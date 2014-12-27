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
	 * @see games.Player.Player(int, Game)
	 */
	public ChuckNorris(int number, Game game)
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