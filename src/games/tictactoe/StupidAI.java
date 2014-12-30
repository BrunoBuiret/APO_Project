package games.tictactoe;

import java.util.ArrayList;
import java.util.Random;

import games.Board;
import games.AIPlayer;
import games.Position;

/**
 * @author Bruno Buiret (11202344)
 * @version 1.0
 * @brief This class represents an AI that plays next to the last move.
 */
public class StupidAI extends AIPlayer
{
    /**
     * @brief Holds the serialization version number.
     */
    private static final long serialVersionUID = 5644773956666028724L;

    /**
	 * @brief Creates a new stupid AI for the tic tac toe.
	 * @see games.Player.Player(int, Game)
	 */
	public StupidAI(int number, TicTacToe game)
	{
		super(number, game);
	}
	
	/**
	 * @brief Determines a random position to play adjacent to the last played one.
	 * @see games.Player.getNextPosition()
	 * @warning This method will fail if the AI is the first to play.
	 */
	public Position getNextPosition()
	{
		Position lastPosition = this.game.getHistory().get(this.game.getHistory().size() - 1).getPosition();
		ArrayList<Position> availablePositions = new ArrayList<Position>();
		Board board = this.game.getBoard();
		
		// Build a list of every available moves
		for(int y = lastPosition.getY() - 1, maxY = lastPosition.getY() + 1; y <= maxY; y++)
		{
			for(int x = lastPosition.getX() - 1, maxX = lastPosition.getX() + 1; x <= maxX; x++)
			{
				if(
					(x >= 0 && y >= 0)
					&&
					(x < board.getWidth() && y < board.getHeight())
					&&
					board.getAt(x, y) == null
				)
				{
					availablePositions.add(new Position(x, y));
				}
			}
		}
		
		// Return a random one
		Random r = new Random();
		return availablePositions.size() > 0 ? availablePositions.get(r.nextInt(availablePositions.size())) : null;
	}
}