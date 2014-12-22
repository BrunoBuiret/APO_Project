package games;

import java.security.InvalidParameterException;
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
	 * @param players List of players for the new game.
	 */
	public Game(List<Player> players)
	{
		this.players = players;
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
	 * @brief Plays
	 * @todo Add action class.
	 * 
	 * This method must be implemented by any class representing a game. It will
	 * contain the logic of an action being played.
	 */
	protected abstract void play();
	
	/**
	 * @brief Cancels the last played action of a game.
	 * 
	 * This method must be implemented by any class representing a game. It will
	 * contain the logic of the cancellation of the last played action.
	 */
	protected abstract void cancel();
	
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