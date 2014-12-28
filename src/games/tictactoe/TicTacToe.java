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

/**
 * @author Bruno Buiret (11202344)
 * @version 1.1
 * @brief This class represents a game of tic tac toe.
 */
public class TicTacToe extends Game
{
	/**
	 * @brief Holds the serialization version number.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * @brief Creates a new tic tac toe game.
	 * @see games.Game.Game()
	 */
	public TicTacToe()
	{
		super();
		this.name = "Tic Tac Toe";
		this.board = new Board(3, 3, new TicTacToePlayerFormatter());
	}

	/**
	 * @brief Plays a position on the board.
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
	 * @brief Checks if a player won a game of tic tac toe.
	 * @see games.Game.chec(Player)
	 */
	protected boolean check(Player p)
	{
		// Check horizontals and verticals
		for(int i = 0; i < 3; i++)
		{
			if(
				(
					this.board.getAt(i, 0) != null && this.board.getAt(i, 0).equals(p)
					&&
					this.board.getAt(i, 1) != null && this.board.getAt(i, 1).equals(p)
					&&
					this.board.getAt(i, 2) != null && this.board.getAt(i, 2).equals(p)
				)
				||
				(
					this.board.getAt(0, i) != null && this.board.getAt(0, i).equals(p)
					&&
					this.board.getAt(1, i) != null && this.board.getAt(1, i).equals(p)
					&&
					this.board.getAt(2, i) != null && this.board.getAt(2, i).equals(p)
				)
			)
			{
				return true;
			}
		}
		
		// Check diagonals
		if(
			(
				this.board.getAt(0, 0) != null && this.board.getAt(0, 0).equals(p)
				&&
				this.board.getAt(1, 1) != null && this.board.getAt(1, 1).equals(p)
				&&
				this.board.getAt(2, 2) != null && this.board.getAt(2, 2).equals(p)
			)
			||
			(
				this.board.getAt(2, 0) != null && this.board.getAt(2, 0).equals(p)
				&&
				this.board.getAt(1, 1) != null && this.board.getAt(1, 1).equals(p) 
				&&
				this.board.getAt(0, 2) != null && this.board.getAt(0, 2).equals(p)
			)
		)
		{
			return true;
		}
		
		return false;
	}
	
	/**
	 * @brief Prepares a game of tic tac toe.
	 * @see games.Game.prepare()
	 */
	protected boolean prepare()
	{
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		boolean keepScanning = false;
		int actionId = -1;
		
		// Asks the user what they want to do
		System.out.println("What do you want to do?");
		System.out.println(" 1. New Human vs Human");
		System.out.println(" 2. New Human vs Stupid AI");
		System.out.println(" 3. New Human vs Smart AI");
		System.out.println(" 4. Load a game");
		System.out.println(" 5. Return");
		
		do
		{
			try
			{
				System.out.print("=> ");
				actionId = Integer.parseInt(r.readLine());
				keepScanning = actionId != 1 && actionId != 2 && actionId != 3 && actionId != 4 && actionId != 5;
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
		
		// Create the first player who's always human
		this.players.add(new HumanPlayer(1, this));
		
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
			
			case 4:
				throw new RuntimeException("Loading a game hasn't been implemented yet.");
			
			case 5:
				return false;
		}
		
		return true;
	}
}