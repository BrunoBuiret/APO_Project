package games;

import java.security.InvalidParameterException;
import java.util.List;

/**
 * @author Bruno Buiret (11202344)
 * @version 1.0
 * @brief This class holds the board of a game.
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
	 * @brief Gets which player played at a position.
	 * @see games.Board.getAt(int, int)
	 */
	public Player getAt(Position position) throws InvalidParameterException
	{
		return this.getAt(position.getX(), position.getY());
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
	 * @brief Sets which player played at a position.
	 * @see games.Board.setAt(int, int, Player)
	 */
	public void setAt(Position position, Player player)
	{
		this.setAt(position.getX(), position.getY(), player);
	}
	
	/**
	 * @brief Gets the board itself.
	 * @return Two-dimension array representing the board.
	 */
	public Player[][] toArray()
	{
		return this.board;
	}
	
	/**
	 * @brief Gets a string representation of a board.
	 * @return Board's string representation.
	 * @todo Add an interface to get a char representing the player to put on the board.
	 */
	public String toString()
	{
		StringBuilder s = new StringBuilder();
		int x, y;
		
		// Header of the board
		s.append("+");
		
		for(x = 0; x < this.width; x++)
		{
			s.append("-");
		}
		
		s.append("+\n");
		
		// 
		for(y = 0; y < this.height; y++)
		{
			s.append("|");
			
			for(x = 0; x < this.width; x++)
			{
				if(this.board[x][y] != null)
				{
					s.append(this.board[x][y].getNumber());
				}
				else
				{
					s.append(' ');
				}
			}
			
			s.append("|\n");
		}
		
		// Footer of the board
		s.append("+");
		
		for(x = 0; x < this.width; x++)
		{
			s.append("-");
		}
		
		s.append("+\n");
		
		return s.toString();
	}
}