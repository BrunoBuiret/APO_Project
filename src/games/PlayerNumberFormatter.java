package games;

/**
 * @author Bruno Buiret (11202344)
 * @version 1.0
 * @brief This class can be used to hold the logic of players and has to be inherited to do so.
 */
public class PlayerNumberFormatter implements PlayerFormatterInterface
{
	/**
	 * @see games.PlayerFormatterInterface.getPlayerRepresentation(Player)
	 * @bug This method only works if the player's number is between 1 and 9.
	 */
	public char getPlayerRepresentation(Player p)
	{
		return (char) ('0' + p.getNumber());
	}
	
}