package games.connectfour;

import games.Player;
import games.PlayerFormatterInterface;

/**
 * @author Bruno Buiret (11202344)
 * @version 1.0
 * @brief This class is used with the Connect Four to represent the players on
 * the board.
 */
public class ConnectFourPlayerFormatter implements PlayerFormatterInterface
{
    /**
     * @brief Holds the serialization version number.
     */
    private static final long serialVersionUID = 1790428708691284266L;
    
    /**
     * @brief Holds the char representing the first player.
     */
    protected final char player1Representation;
    
    /**
     * @brief Holds the char representing the second player.
     */
    protected final char player2Representation;
    
    /**
     * @brief Creates a new formatter to use with the connect four.
     */
    public ConnectFourPlayerFormatter()
    {
        if(
            System.getProperty("os.name").toLowerCase().indexOf("nix") >= 0
            ||
            System.getProperty("os.name").toLowerCase().indexOf("nux") >= 0
            ||
            System.getProperty("os.name").toLowerCase().indexOf("aix") >= 0
        )
        {
            this.player1Representation = '●';
            this.player2Representation = '○';
        }
        else
        {
            this.player1Representation = 'x';
            this.player2Representation = 'o';
        }
    }
    
    /**
     * @brief Gets a char representation of a player for the connect four.
     * @see games.PlayerFormatterInterface.getPlayerRepresentation(Player)
     */
    public char getPlayerRepresentation(Player p)
    {
        return p.getNumber() == 1 ? this.player1Representation : this.player2Representation;
    }
}