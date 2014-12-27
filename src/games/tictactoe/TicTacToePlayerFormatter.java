package games.tictactoe;

import games.Player;
import games.PlayerFormatterInterface;

/**
 * @author Bruno Buiret (11202344)
 * @version 1.0
 * @brief This class is used with the Tic Tac Toe to represent the players on the board.
 */
public class TicTacToePlayerFormatter implements PlayerFormatterInterface
{
	/**
	 * @brief Holds the serialization version number.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * @brief Gets a char representation of a player for the tic tac toe.
	 * @see games.PlayerFormatterInterface.getPlayerRepresentation(Player)
	 */
	public char getPlayerRepresentation(Player p)
	{
		// return p.getNumber() == 1 ? '✕' : '○';
		return p.getNumber() == 1 ? 'x' : 'o';
	}
}