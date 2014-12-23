package games;

import java.util.Random;

/**
 * @author Bruno Buiret (11202344)
 * @version 1.0
 * @brief This class represents an AI that plays randomly.
 */
public class RandomPlayer extends Player
{
	/**
	 * @brief Reference to a random numbers generator.
	 */
	protected Random random;
	
	/**
	 * @see games.Player.Player()
	 */
	public RandomPlayer(int number, Board board)
	{
		super(number, board);
		this.random = new Random();
	}
	
	/**
	 * @brief Determines randomly a position.
	 * @return Reference to a random position.
	 * @see games.Player.getNextPosition()
	 */
	public Position getNextPosition()
	{
		return new Position(this.random.nextInt((this.board.getWidth() - 1) + 1), this.random.nextInt((this.board.getHeight() - 1) + 1));
	}
}