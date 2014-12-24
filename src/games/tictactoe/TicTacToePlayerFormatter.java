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
	public char getPlayerRepresentation(Player p)
	{
		return p.getNumber() == 1 ? '✕' : '○';
	}
	
}