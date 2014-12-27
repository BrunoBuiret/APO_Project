package games.connectfour;

import games.Game;
import games.Position;
import games.RandomPlayer;

/**
 * @author Bruno Buiret (11202344)
 * @version 1.0
 * @brief This class represents a stupid AI which plays randomly for the Connect Four game.
 */
public class StupidAI extends RandomPlayer
{
	/**
	 * @see games.Player.Player(int, Game)
	 */
	public StupidAI(int number, Game game)
	{
		super(number, game);
	}
	
	/**
	 * @brief Overrides this method to ignore line number.
	 * @see games.RandomPlayer.getNextPosition()
	 */
	public Position getNextPosition()
	{
		return new Position(this.random.nextInt((this.game.getBoard().getWidth() - 1) + 1), 0);
	}
}