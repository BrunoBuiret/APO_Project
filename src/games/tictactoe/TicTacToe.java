package games.tictactoe;

import java.security.InvalidParameterException;

import games.Board;
import games.Game;
import games.HistoryEntry;
import games.Player;
import games.Position;
import games.RandomPlayer;

/**
 * @author Bruno Buiret (11202344)
 * @version 1.0
 */
public class TicTacToe extends Game
{
	/**
	 * @brief Creates a new tic tac toe game.
	 */
	public TicTacToe()
	{
		super();
		this.board = new Board(3, 3);
	}

	/**
	 *  @see games.Game.run()
	 *  @todo Implement the main loop.
	 */
	public void run()
	{
		// Create the players
		Player p1 = new RandomPlayer(1, this.board);
		Player p2 = new RandomPlayer(2, this.board);
		
		// Main loop
		this.play(p1, p1.getNextPosition());
		this.play(p2, p2.getNextPosition());
		this.play(p1, p1.getNextPosition());
		this.play(p2, p2.getNextPosition());
	}

	/**
	 * @see games.Game.play()
	 */
	protected void play(Player player, Position position) throws InvalidParameterException
	{
		if(this.board.getAt(position) == null)
		{
			this.board.setAt(position, player);
			this.history.add(new HistoryEntry(player, position));
		}
		else
		{
			throw new InvalidParameterException(String.format("Player #%d already played at ", player.getNumber(), position));
		}
	}
}