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
	 * @warning This method only works if the player's number is between 1 and 9.
	 */
	public char getPlayerRepresentation(Player p)
	{
		return p.getNumber() >= 0 && p.getNumber() <= 9 ? (char) ('0' + p.getNumber()) : '?';
	}
}