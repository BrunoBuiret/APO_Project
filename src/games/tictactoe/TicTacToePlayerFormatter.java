package games.tictactoe;

import games.Player;
import games.PlayerFormatterInterface;

/**
 * @author Bruno Buiret (11202344)
 * @version 1.0
 * @brief This class is used with the Tic Tac Toe to represent the players on
 * the board.
 */
public class TicTacToePlayerFormatter implements PlayerFormatterInterface
{
    /**
     * @brief Holds the serialization version number.
     */
    private static final long serialVersionUID = -3624960642904225058L;
    
    /**
     * @brief Holds the char representing the first player.
     */
    protected final char player1Representation;
    
    /**
     * @brief Holds the char representing the second player.
     */
    protected final char player2Representation;
    
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
            this.player1Representation = '✕';
            this.player2Representation = '○';
        }
        else
        {
            this.player1Representation = 'x';
            this.player2Representation = 'o';
        }
    }
    
    /**
     * @brief Gets a char representation of a player for the tic tac toe.
     * @see games.PlayerFormatterInterface.getPlayerRepresentation(Player)
     */
    public char getPlayerRepresentation(Player p)
    {
        return p.getNumber() == 1 ? this.player1Representation : this.player2Representation;
    }
}