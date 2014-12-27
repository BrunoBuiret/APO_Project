package games;

public abstract class AIPlayer extends Player
{
	/**
	 * @brief Holds the serialization version number.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see games.Player.Player(int, Game)
	 */
	public AIPlayer(int number, Game game)
	{
		super(number, game);
	}
}