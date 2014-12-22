package games;

/**
 * @author Bruno Buiret (11202344)
 * @version 1.0
 * @brief This class holds an entry from the history.
 */
public class HistoryEntry
{
	/**
	 * @brief Holds a reference to the position which was played.
	 */
	protected final Position position;
	
	/**
	 * @brief Holds a reference to the player who played.
	 */
	protected final Player player;
	
	/**
	 * @brief Creates a new history entry.
	 * @param position Reference to the position.
	 * @param player Reference to the player.
	 */
	public HistoryEntry(Position position, Player player)
	{
		this.position = position;
		this.player = player;
	}
	
	/**
	 * @brief Gets the position associated with an history entry.
	 * @return Reference to the position.
	 */
	public Position getPosition()
	{
		return this.position;
	}
	
	/**
	 * @brief Gets the player associated with an history entry.
	 * @return Reference to the player.
	 */
	public Player getPlayer()
	{
		return this.player;
	}
}