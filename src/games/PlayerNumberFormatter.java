package games;

/**
 * @author Bruno Buiret (11202344)
 * @version 1.0
 * @brief This class formats a player on the board by their number.
 */
public class PlayerNumberFormatter implements PlayerFormatterInterface
{
    /**
     * @brief Holds the serialization version number.
     */
    private static final long serialVersionUID = 3966110915288229124L;
    
    /**
     * @see games.PlayerFormatterInterface.getPlayerRepresentation(Player)
     * @warning This method only works if the player's number is between 0 and 9.
     */
    public char getPlayerRepresentation(Player p)
    {
        return p.getNumber() >= 0 && p.getNumber() <= 9 ? (char) ('0' + p.getNumber()) : '?';
    }
}