package games;

/**
 * @author Bruno Buiret (11202344)
 * @version 1.0
 * @brief This class holds an entry from the history.
 */
public class HistoryEntry
{
	/**
	 * @brief Holds a reference to the player who played.
	 */
	protected final Player player;
	
	/**
	 * @brief Holds a reference to the position which was played.
	 */
	protected final Position position;
	
	/**
	 * @brief Creates a new history entry.
	 * @param position Reference to the position.
	 * @param player Reference to the player.
	 */
	public HistoryEntry(Player player, Position position)
	{
		this.player = player;
		this.position = position;
	}
	
	/**
	 * @brief Gets the player associated with an history entry.
	 * @return Reference to the player.
	 */
	public Player getPlayer()
	{
		return this.player;
	}
	
	/**
	 * @brief Gets the position associated with an history entry.
	 * @return Reference to the position.
	 */
	public Position getPosition()
	{
		return this.position;
	}
}