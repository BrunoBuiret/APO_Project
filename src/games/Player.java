package games;

/**
 * @author Bruno Buiret (11202344)
 * @version 1.0
 * @brief This class can be used to hold the logic of players and has to be inherited to do so.
 */
public abstract class Player
{
	/**
	 * @brief Holds the number of the player.
	 */
	protected final int number;
	
	/**
	 * @brief Holds a reference to the game.
	 */
	protected final Game game;
	
	/**
	 * @brief Creates a new player.
	 * @param number Number of the player. 
	 * @param game Reference to the game.
	 */
	public Player(int number, Game game)
	{
		this.number = number;
		this.game = game;
	}
	
	/**
	 * @brief Gets the number of a player.
	 * @return Player's number.
	 */
	public int getNumber()
	{
		return this.number;
	}
	
	/**
	 * @brief Determines the next position a player is going to play.
	 * @return Reference to the position to play or `null`.
	 * 
	 * This method must be implemented by any inheriting class. It deduces a move
	 * if the player is an AI or asks the user for a move if they are human.
	 */
	public abstract Position getNextPosition();
	
	/**
	 * @brief Gets a string representation of a player.
	 * @return Player's string representation.
	 */
	public String toString()
	{
		return String.format("Player #%d", this.number);
	}
}