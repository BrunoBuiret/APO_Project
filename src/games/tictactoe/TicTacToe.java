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
		boolean keepScanning = false;
		
		do
		{
			System.out.println("What type of game is it going to be ?");
			System.out.println(" 1. Human vs Human");
			System.out.println(" 2. Human vs Human");
		}
		while(keepScanning);
		
		// Main loop
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