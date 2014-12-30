package games;

/**
 * @author Bruno Buiret (11202344)
 * @version 1.0
 * @brief This exception is thrown when the user tries to load a save which
 * doesn't match the game.
 */
public class GameSaveMismatchException extends Exception
{
    /**
     * @brief Holds the serialization version number.
     */
    private static final long serialVersionUID = -2517681755271588113L;

    /**
     * @brief Creates a new exception because a save file isn't for this kind of
     * game.
     * @param message Detail message.
     */
    public GameSaveMismatchException(String message)
    {
        super(message);
    }
}