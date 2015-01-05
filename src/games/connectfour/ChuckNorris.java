package games.connectfour;

import games.Board;
import games.Game;
import games.AIPlayer;
import games.Position;

/**
 * @author Bruno Buiret (11202344)
 * @version 1.0
 * @brief This class represents an AI that can win in only one move.
 */
public class ChuckNorris extends AIPlayer
{
    /**
     * @brief Holds the serialization version number.
     */
    private static final long serialVersionUID = 1319664328717854832L;
    
    /**
     * @brief Creates a new IA named Chuck Norris.
     * @see games.Player.Player(int, Game)
     */
    public ChuckNorris(int number, Game game)
    {
        super(number, game);
    }
    
    /**
     * @brief Overrides any move on the first column to place three pawns
     * and then gives the fourth needed position so that the AI wins every
     * time.
     * @see games.Player.getNextPosition()
     */
    public Position getNextPosition()
    {
        Board b = this.game.getBoard();
        b.setAt(0, 5, this);
        b.setAt(0, 4, this);
        b.setAt(0, 3, this);
        return new Position(0, 2);
    }
}