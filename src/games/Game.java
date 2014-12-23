package games;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Bruno Buiret (11202344)
 * @version 1.0
 * @brief This class can be used to hold the logic of a board game.
 */
public abstract class Game
{
	/**
	 * @brief Holds a list of players.
	 */
	protected List<Player> players;
	
	/**
	 * @brief Holds a reference to the board.
	 */
	protected Board board;
	
	/**
	 * @brief Holds a list of every played action.
	 */
	protected List<HistoryEntry> history;
	
	/**
	 * @brief Creates a new game.
	 */
	public Game()
	{
		this.players = new ArrayList<Player>();
		this.board = null;
		this.history = new ArrayList<HistoryEntry>();
	}
	
	/**
	 * @brief Runs the game.
	 * 
	 * This method must be implemented by any class representing a game. It will
	 * contain the logic of the game : initialization, main loop and winning/losing
	 * conditions.
	 */
	public abstract void run();
	
	/**
	 * @brief Adds a move to a game.
	 * @param player Reference to the player.
	 * @param position Reference to the position.
	 * 
	 * This method must be implemented by any class representing a game. It will
	 * contain the logic of an action being played.
	 */
	protected abstract void play(Player player, Position position);
	
	/**
	 * @brief Cancels the last played action of a game.
	 */
	protected void cancel()
	{
		if(this.history.size() > 0)
		{
			HistoryEntry e = this.history.remove(this.history.size() - 1);
			this.board.setAt(e.getPosition().getX(), e.getPosition().getY(), null);
		}
	}
	
	/**
	 * @brief Gets the board of a game.
	 * @return Reference to the board.
	 */
	public Board getBoard()
	{
		return this.board;
	}
	
	/**
	 * @brief Gets a player from a game.
	 * @param index Index of the player in the list.
	 * @return Reference to the wanted player.
	 * @throws InvalidParameterException
	 */
	public Player getPlayer(int index) throws InvalidParameterException
	{
		if(index < this.players.size())
		{
			return this.players.get(index);
		}
		
		throw new InvalidParameterException("There are no players");
	}
}