package games;

/**
 * @author Bruno Buiret (11202344)
 * @version 1.0
 * @brief This class represents an intermediate for every AI players.
 */
public abstract class AIPlayer extends Player
{
    /**
     * @brief Holds the serialization version number.
     */
    private static final long serialVersionUID = 1635823779529407299L;

    /**
     * @brief Creates a new AI player.
     * @see games.Player.Player(int, Game)
     */
    public AIPlayer(int number, Game game)
    {
        super(number, game);
    }
}