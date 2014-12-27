package games.connectfour;

import java.io.IOException;

import games.Game;

/**
 * @author Bruno Buiret (11202344)
 * @version 1.0
 * @brief Human Player is inherited so that the line number is ignored.
 */
public class HumanPlayer extends games.HumanPlayer
{
	/**
	 * @see games.HumanPlayer.HumanPlayer(int, Game)
	 */
	public HumanPlayer(int number, Game game)
	{
		super(number, game);
	}
	
	/**
	 * @brief Overrides the superclass method to ignore the line number.
	 * @see games.HumanPlayer.getNextPosition()
	 */
	public Position getNextPosition()
	{
		Position p = null;
		
		try
		{
			System.out.print("Type which column you want to play [@Column(column)]: ");
			p = Position.parse(this.inputReader.readLine());
		}
		catch(IOException e)
		{
			return null;
		}
		
		return p;
	}
}