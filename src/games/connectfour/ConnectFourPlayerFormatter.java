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
	 * @see games.PlayerFormatterInterface.getPlayerRepresentation(Player)
	 */
	public char getPlayerRepresentation(Player p)
	{
		return p.getNumber() == 1 ? '●' : '○';
	}
}