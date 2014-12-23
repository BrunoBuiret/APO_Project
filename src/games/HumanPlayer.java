package games;

import java.util.Scanner;

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
	public HumanPlayer(int number, Board board)
	{
		super(number, board);
	}

	/**
	 * @brief Reads a position from user's input.
	 * @return Reference to the read position or `null`.
	 * @see games.Player.getNextPosition()
	 */
	public Position getNextPosition()
	{
		Scanner s = new Scanner(System.in);
		System.out.println("Type the position you want to play [@Position(column, line)]: ");
		Position p = Position.parse(s.nextLine());
		s.close();
		return p;
	}
	
}