package games.connectfour;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;

import games.Board;
import games.Game;
import games.HistoryEntry;
import games.Player;
import games.Position;

/**
 * @author Bruno Buiret (11202344)
 * @version 1.1
 * @brief This class represents a game of connect four.
 */
public class ConnectFour extends Game
{
	/**
	 * @brief Creates a new connect four game.
	 * @see games.Game.Game()
	 */
	public ConnectFour()
	{
		super();
		this.name = "Connect Four";
		this.board = new Board(7, 6, new ConnectFourPlayerFormatter());
	}
	
	/**
	 * @brief Plays a position on the board.
	 * @see games.Game.play(Player, Position)
	 * 
	 * This method takes into account gravity by overriding the ordinate of the given
	 * position and then placing the pawn.
	 */
	protected void play(Player player, Position position) throws InvalidParameterException
	{
		int x = position.getX();
		int y = 0;
		
		while(y + 1 < this.board.getHeight() && this.board.getAt(x, y + 1) == null)
		{
			y++;
		}
		
		if(this.board.getAt(x, y) == null)
		{
			this.board.setAt(x, y, player);
			
			if(y != position.getY())
			{
				this.history.add(new HistoryEntry(player, new Position(x, y)));
			}
			else
			{
				this.history.add(new HistoryEntry(player, position));
			}
		}
		else
		{
			throw new InvalidParameterException(String.format("Column %d is already filled.", x));
		}
	}
	
	/**
	 * @brief Checks if a player won a game of connect four.
	 * @see games.Game.check(Player)
	 * @todo Implement this method.
	 */
	protected boolean check(Player player)
	{
		// Check horizontals, verticals, diagonals
		return false;
	}
	
	/**
	 * @brief Prepares a game of connect four.
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
		System.out.println(" 4. New Human vs Chuck Norris");
		System.out.println(" 5. Load a game");
		System.out.println(" 6. Return");
		List<Integer> possibleChoices = Arrays.asList(1, 2, 3, 4, 5, 6);
		
		do
		{
			try
			{
				System.out.print("=> ");
				actionId = Integer.parseInt(r.readLine());
				keepScanning = !possibleChoices.contains(actionId);
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
				this.players.add(new ChuckNorris(2, this));
			break;
			
			case 5:
				throw new RuntimeException("Loading a game hasn't been implemented yet.");
			
			case 6:
				return false;
		}
		
		return true;
	}
}