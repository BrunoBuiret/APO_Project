package games;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Bruno Buiret (11202344)
 * @version 1.0
 * @brief This class represents a human player.
 */
public class HumanPlayer extends Player
{
	/**
	 * @brief Holds the serialization version number.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * @brief Holds a reference to a buffered reader for user input.
	 */
	protected BufferedReader inputReader;
	
	/**
	 * @brief Creates a new human player.
	 * @see games.Player.Player(int, Game)
	 */
	public HumanPlayer(int number, Game game)
	{
		super(number, game);
		this.inputReader = new BufferedReader(new InputStreamReader(System.in));
	}

	/**
	 * @brief Reads a position from user's input.
	 * @return Reference to the read position or `null`.
	 * @see games.Player.getNextPosition()
	 */
	public Position getNextPosition()
	{
		Position p = null;
		
		try
		{
			System.out.print("Type the position you want to play [@Position(column, line)]: ");
			p = Position.parse(this.inputReader.readLine());
		}
		catch(IOException e)
		{
			return null;
		}
		
		return p;
	}
}