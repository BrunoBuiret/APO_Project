package games.connectfour;

import games.Player;
import games.PlayerFormatterInterface;

/**
 * @author Bruno Buiret (11202344)
 * @version 1.0
 * @brief This class is used with the Connect Four to represent the players on the board.
 */
public class ConnectFourPlayerFormatter implements PlayerFormatterInterface
{
	/**
	 * @brief Holds the serialization version number.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * @brief Gets a char representation of a player for the connect four.
	 * @see games.PlayerFormatterInterface.getPlayerRepresentation(Player)
	 */
	public char getPlayerRepresentation(Player p)
	{
		// return p.getNumber() == 1 ? '●' : '○';
		return p.getNumber() == 1 ? 'x' : 'o';
	}
}