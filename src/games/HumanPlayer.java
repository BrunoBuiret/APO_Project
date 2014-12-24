package games;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Bruno Buiret (11202344)
 * @version 1.0
 * @brief THis class represents a human player.
 */
public class HumanPlayer extends Player
{
	/**
	 * @see games.Player.Player()
	 */
	public HumanPlayer(int number, Game game)
	{
		super(number, game);
	}

	/**
	 * @brief Reads a position from user's input.
	 * @return Reference to the read position or `null`.
	 * @see games.Player.getNextPosition()
	 */
	public Position getNextPosition()
	{
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		Position p;
		
		try
		{
			System.out.println("Type the position you want to play [@Position(column, line)]: ");
			System.out.print("=> ");
			p = Position.parse(r.readLine());
			r.close();
		}
		catch(IOException e)
		{
			return null;
		}
		
		return p;
	}
}