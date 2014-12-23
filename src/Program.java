import games.connectfour.ConnectFour;
import games.tictactoe.TicTacToe;

/**
 * @author Bruno Buiret (11202344)
 * @version 1.0
 * @brief This class represents the program containing the menu and games.
 */
public class Program
{
	public static void main(String[] args)
	{
		ConnectFour c = new ConnectFour();
		c.run();
		System.out.println(c.getBoard());
		
		TicTacToe t = new TicTacToe();
		t.run();
		System.out.println(t.getBoard());
	}
}