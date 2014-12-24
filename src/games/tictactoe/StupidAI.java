package games.tictactoe;

import games.Game;
import games.Player;
import games.Position;

public class StupidAI extends Player
{
	public StupidAI(int number, Game game)
	{
		super(number, game);
	}
	
	public Position getNextPosition()
	{
		return null;
	}
}