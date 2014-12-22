package games;

import java.security.InvalidParameterException;
import java.util.List;

/**
 * @author Bruno Buiret (11202344)
 * @version 1.0
 */
public class Board
{
	/**
	 * @brief Holds the board's width.
	 */
	protected final int width;
	
	/**
	 * @brief Holds the board's height.
	 */
	protected final int height;
	
	/**
	 * @brief Holds the board itself.
	 */
	protected Player[][] board;
	
	/**
	 * @brief Creates a new board.
	 * @param width Board's width.
	 * @param height Board's height.
	 * @throws InvalidParameterException
	 */
	public Board(int width, int height) throws InvalidParameterException
	{
		if(width > 0)
		{
			this.width = width;
		}
		else
		{
			throw new InvalidParameterException("A board's width cannot be null or negative.");
		}
		
		if(height > 0)
		{
			this.height = height;
		}
		else
		{
			throw new InvalidParameterException("A board's height cannot be null or negative.");
		}
		
		this.board = new Player[this.width][this.height];
		
		for(int x = 0; x < this.width; x++)
		{
			for(int y = 0; y < this.height; y++)
			{
				this.board[x][y] = null;
			}
		}
	}
	
	/**
	 * @brief Creates a new board and initializes it with an history.
	 * @param width Board's width.
	 * @param height Board's height.
	 * @param history History to initialize the board with.
	 */
	public Board(int width, int height, List<HistoryEntry> history)
	{
		this(width, height);
		
		for(HistoryEntry e : history)
		{
			this.board[e.getPosition().getX()][e.getPosition().getY()] = e.getPlayer();
		}
	}
	
	/**
	 * @brief Gets a board's width.
	 * @return Board's width.
	 */
	public int getWidth()
	{
		return this.width;
	}
	
	/**
	 * @brief Gets a board's height.
	 * @return Board's height.
	 */
	public int getHeight()
	{
		return this.height;
	}
	
	/**
	 * @brief Gets which player played at a position.
	 * @param x Position's abscissa.
	 * @param y Position's ordinate.
	 * @return Reference to the player or `null`.
	 * @throws InvalidParameterException
	 */
	public Player getAt(int x, int y) throws InvalidParameterException
	{
		if(x >= 0)
		{
			if(x < this.width)
			{
				if(y >= 0)
				{
					if(y < this.height)
					{
						return this.board[x][y];
					}
					else
					{
						throw new InvalidParameterException("Ordinate cannot be outside of the board.");
					}
				}
				else
				{
					throw new InvalidParameterException("Ordinate cannot be negative.");
				}
			}
			else
			{
				throw new InvalidParameterException("Abscissa cannot be outside of the board.");				
			}
		}
		else
		{
			throw new InvalidParameterException("Abscissa cannot be negative.");
		}
	}
	
	/**
	 * @brief Sets which player played at a position.
	 * @param x Position's abscissa.
	 * @param y Position's ordinate.
	 * @param p Reference to the player.
	 * @throws InvalidParameterException
	 */
	public void setAt(int x, int y, Player p) throws InvalidParameterException
	{
		if(x >= 0)
		{
			if(x < this.width)
			{
				if(y >= 0)
				{
					if(y < this.height)
					{
						this.board[x][y] = p;
					}
					else
					{
						throw new InvalidParameterException("Ordinate cannot be outside of the board.");
					}
				}
				else
				{
					throw new InvalidParameterException("Ordinate cannot be negative.");
				}
			}
			else
			{
				throw new InvalidParameterException("Abscissa cannot be outside of the board.");				
			}
		}
		else
		{
			throw new InvalidParameterException("Abscissa cannot be negative.");
		}
	}
	
	/**
	 * @brief Gets the board itself.
	 * @return Two-dimension array representing the board.
	 */
	public Player[][] toArray()
	{
		return this.board;
	}
}