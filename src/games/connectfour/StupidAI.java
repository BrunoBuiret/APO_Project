package games.connectfour;

import games.Position;
import games.RandomPlayer;

/**
 * @author Bruno Buiret (11202344)
 * @version 1.0
 * @brief This class represents a stupid AI which plays randomly for the connect
 * four game.
 */
public class StupidAI extends RandomPlayer
{
    /**
     * @brief Holds the serialization version number.
     */
    private static final long serialVersionUID = -4790271675890539765L;
    
    /**
     * @brief Creates a new stupid AI for the connect four
     * @see games.Player.Player(int, Game)
     */
    public StupidAI(int number, ConnectFour game)
    {
        super(number, game);
    }
    
    /**
     * @brief Overrides the superclass method to ignore line number.
     * @see games.RandomPlayer.getNextPosition()
     */
    public Position getNextPosition()
    {
        return new Position(this.random.nextInt(this.game.getBoard().getWidth()), 0);
    }
}