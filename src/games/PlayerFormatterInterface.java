package games;

import java.io.Serializable;

/**
 * @author Bruno Buiret (11202344)
 * @version 1.0
 * @brief This interface can be implemented to get a char representing a player for a board.
 */
public interface PlayerFormatterInterface extends Serializable
{
	/**
	 * @brief Gets a char representing a player.
	 * @param p Reference to the player for which a representation is needed.
	 * @return Player's representation.
	 */
	public char getPlayerRepresentation(Player p);
}