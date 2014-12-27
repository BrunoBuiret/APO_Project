package games.connectfour;

import games.Game;
import games.Player;
import games.Position;

/**
 * @author Bruno Buiret (11202344)
 * @version 1.0
 */
public class SmartAI extends Player
{
	/**
	 * @see games.Player.Player(int, Game)
	 */
	public SmartAI(int number, Game game)
	{
		super(number, game);
	}
	
	/**
	 * @see games.Player.getNextPosition()
	 */
	public Position getNextPosition()
	{
		return null;
	}
	
}