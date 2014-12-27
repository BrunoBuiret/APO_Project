package games.connectfour;

import games.Game;
import games.Player;
import games.Position;

/**
 * @author Bruno Buiret (11202344)
 * @version 1.0
 * @brief This class represents an AI that can win in only three moves.
 */
public class ChuckNorris extends Player
{
	/**
	 * @brief Holds the serialization version number.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see games.Player.Player(int, Game)
	 */
	public ChuckNorris(int number, Game game)
	{
		super(number, game);
		throw new RuntimeException("This method hasn't been implemented yet.");
	}

	/**
	 * @see games.Player.getNextPosition()
	 * @todo Implement this method.
	 */
	public Position getNextPosition()
	{
		return null;
	}
}