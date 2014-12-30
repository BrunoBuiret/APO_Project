package games;

import java.util.Random;

/**
 * @author Bruno Buiret (11202344)
 * @version 1.0
 * @brief This class represents an AI that plays randomly.
 */
public class RandomPlayer extends AIPlayer
{
    /**
     * @brief Holds the serialization version number.
     */
    private static final long serialVersionUID = -8429934431302248085L;
    
    /**
     * @brief Reference to a random numbers generator.
     */
    protected Random random;
    
    /**
     * @brief Creates a new AI that plays randomly.
     * @see games.Player.Player(int, Game)
     */
    public RandomPlayer(int number, Game game)
    {
        super(number, game);
        this.random = new Random();
    }
    
    /**
     * @brief Determines randomly a position.
     * @return Reference to a random position.
     * @see games.Player.getNextPosition()
     */
    public Position getNextPosition()
    {
        return new Position(this.random.nextInt(this.game.getBoard().getWidth()), this.random.nextInt(this.game.getBoard().getHeight()));
    }
}