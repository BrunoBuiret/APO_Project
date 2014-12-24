package games.tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidParameterException;

import games.Board;
import games.Game;
import games.HistoryEntry;
import games.HumanPlayer;
import games.Player;
import games.Position;
import games.RandomPlayer;

/**
 * @author Bruno Buiret (11202344)
 * @version 1.0
 */
public class TicTacToe extends Game
{
	/**
	 * @brief Creates a new tic tac toe game.
	 */
	public TicTacToe()
	{
		super();
		this.board = new Board(3, 3, new TicTacToePlayerFormatter());
	}

	/**
	 *  @see games.Game.run()
	 *  @todo Implement the main loop.
	 */
	public void run()
	{
		// Create the players
		boolean keepScanning = false;
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		int actionId = -1;
		
		// Display the adversary menu
		System.out.println("What type of game is it going to be ?");
		System.out.println(" 1. Human vs Human");
		System.out.println(" 2. Human vs Stupid AI");
		System.out.println(" 3. Human vs Smart AI");
		
		// Asks the user what he wants
		do
		{
			try
			{
				System.out.print("=> ");
				actionId = Integer.parseInt(r.readLine());
				keepScanning = actionId != 1 && actionId != 2 && actionId != 3;
			}
			catch (NumberFormatException e)
			{
				keepScanning = true;
			}
			catch (IOException e)
			{
				keepScanning = true;
			}
		}
		while(keepScanning);
		
		// The first player is always human
		this.players.add(new HumanPlayer(1, this));
		
		// Create the second player
		/*
		switch(actionId)
		{
			case 1:
				this.players.add(new HumanPlayer(2, this));
			break;
			
			case 2:
				this.players.add(new StupidAI(2, this));
			break;
			
			case 3:
				this.players.add(new SmartAI(2, this));
			break;
		}
		*/
		this.players.add(new RandomPlayer(2, this));
		
		// Main loop
		boolean keepLooping = true;
		int playerIndex = 0;
		
		while(keepLooping)
		{
			boolean keepPlaying = false;
			
			// Let the player play
			do
			{
				Position position = this.players.get(playerIndex).getNextPosition();
				
				if(position != null)
				{
					try
					{
						this.play(this.players.get(playerIndex), position);
						keepPlaying = false;
					}
					catch(InvalidParameterException e)
					{
						System.out.println(e.getMessage());
						keepPlaying = true;
					}
				}
				else
				{
					keepPlaying = true;
				}
			}
			while(keepPlaying);
			
			// Check if he won
			if(this.check(this.players.get(playerIndex)))
			{
				System.out.println(String.format("%s has won.", this.players.get(playerIndex)));
				keepLooping = false;
			}
			
			// Change player
			playerIndex = ++playerIndex % 2;
			
			// Check if the game is finished
			if(this.history.size() == 9)
			{
				System.out.println("Nobody won.");
				keepLooping = false;
			}
		}
	}

	/**
	 * @see games.Game.play(Player, Position)
	 */
	protected void play(Player player, Position position) throws InvalidParameterException
	{
		if(this.board.getAt(position) == null)
		{
			this.board.setAt(position, player);
			this.history.add(new HistoryEntry(player, position));
		}
		else
		{
			throw new InvalidParameterException(String.format("%s already played at %s", player, position));
		}
	}
	
	/**
	 * @see games.Game.chec(Player)
	 */
	protected boolean check(Player p)
	{
		// Check horizontals and verticals
		for(int i = 0; i < 3; i++)
		{
			if(
				this.board.getAt(i, 0) == p && this.board.getAt(i, 1) == p && this.board.getAt(i, 2) == p
				||
				this.board.getAt(0, i) == p && this.board.getAt(1, i) == p && this.board.getAt(2, i) == p
			)
			{
				return true;
			}
		}
		
		// Check diagonals
		if(
			this.board.getAt(0, 0) == p && this.board.getAt(1, 1) == p && this.board.getAt(2, 2) == p
			||
			this.board.getAt(2, 0) == p && this.board.getAt(1, 1) == p && this.board.getAt(0, 2) == p
		)
		{
			return true;
		}
		
		return false;
	}
}