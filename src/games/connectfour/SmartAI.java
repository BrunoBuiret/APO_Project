package games.connectfour;

import games.AIPlayer;
import games.Position;

/**
 * @author Bruno Buiret (11202344)
 * @version 1.0
 * @brief This class represents an AI that "thinks" in order to win a game of
 * connect four.
 */
public class SmartAI extends AIPlayer
{
	/**
	 * @brief Holds the serialization version number.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * @brief Creates a new smart AI for the connect four.
	 * @see games.Player.Player(int, Game)
	 */
	public SmartAI(int number, ConnectFour game)
	{
		super(number, game);
		throw new RuntimeException("This class hasn't been implemented yet.");
	}
	
	/**
	 * @brief Determines the next best position to play.
	 * @see games.Player.getNextPosition()
	 * @todo Implement this method.
	 */
	public Position getNextPosition()
	{
		return null;
	}
}