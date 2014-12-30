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
    private static final long serialVersionUID = -3624960642904225058L;
    
    /**
     * @brief Holds a boolean indicating if the OS is Unix-like.
     */
    protected final boolean isUnix;
    
    /**
     * @brief Creates a new formatter to use with the tic tac toe.
     */
    public TicTacToePlayerFormatter()
    {
        if(
                System.getProperty("os.name").toLowerCase().indexOf("nix") >= 0
                ||
                System.getProperty("os.name").toLowerCase().indexOf("nux") >= 0
                ||
                System.getProperty("os.name").toLowerCase().indexOf("aix") >= 0
            )
            {
                this.isUnix = true;
            }
            else
            {
                this.isUnix = false;
            }
    }

    /**
	 * @brief Gets a char representation of a player for the tic tac toe.
	 * @see games.PlayerFormatterInterface.getPlayerRepresentation(Player)
	 * @todo Improve by determining if the OS is Windows or Linux to put the normal or better chars.
	 */
	public char getPlayerRepresentation(Player p)
	{
		if(this.isUnix)
        {
		    return p.getNumber() == 1 ? '✕' : '○';
        }
        else
        {
            return p.getNumber() == 1 ? 'x' : 'o';
        }
	}
}