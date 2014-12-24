package games.connectfour;

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
 * @brief 
 */
public class ConnectFour extends Game
{
	/**
	 * @brief Creates a new connect four game.
	 */
	public ConnectFour()
	{
		super();
		this.board = new Board(7, 6, new ConnectFourPlayerFormatter());
	}
	
	/**
	 *  @see games.Game.run()
	 *  @todo Implement the main loop.
	 */
	public void run()
	{
		// Create the players
		Player p1 = new RandomPlayer(1, this);
		Player p2 = new RandomPlayer(2, this);
		
		// Main loop
		this.play(p1, p1.getNextPosition());
		this.play(p2, p2.getNextPosition());
		this.play(p1, p1.getNextPosition());
		this.play(p2, p2.getNextPosition());
		
		System.out.println(this.board);
	}
	
	/**
	 * @see games.Game.play(Player, Position)
	 */
	protected void play(Player player, Position position) throws InvalidParameterException
	{
		int x = position.getX();
		int y = 0;
		
		while(y + 1 < this.board.getHeight() && this.board.getAt(x, y + 1) == null)
		{
			y++;
		}
		
		if(this.board.getAt(x, y) == null)
		{
			this.board.setAt(x, y, player);
			
			if(y != position.getY())
			{
				this.history.add(new HistoryEntry(player, new Position(x, y)));
			}
			else
			{
				this.history.add(new HistoryEntry(player, position));
			}
		}
		else
		{
			throw new InvalidParameterException(String.format("%s already played at %s", player, position));
		}
	}
	
	protected boolean check(Player player)
	{
		return false;
	}
}