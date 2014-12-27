package games;

import java.io.Serializable;
import java.security.InvalidParameterException;
import java.util.List;

/**
 * @author Bruno Buiret (11202344)
 * @version 1.1
 * @brief This class holds the board of a game.
 */
public class Board implements Serializable, Cloneable
{
	/**
	 * @brief Holds the serialization version number.
	 */
	private static final long serialVersionUID = 1L;

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
	 * @brief Holds a formatter to get a player's representation.
	 */
	protected PlayerFormatterInterface formatter;
	
	/**
	 * @brief Creates a new board.
	 * @param width Board's width.
	 * @param height Board's height.
	 * @param formatter Reference to a formatter.
	 * @throws InvalidParameterException
	 */
	public Board(int width, int height, PlayerFormatterInterface formatter) throws InvalidParameterException
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
		
		this.formatter = formatter;
	}
	
	/**
	 * @brief Creates a new board and initializes it with an history.
	 * @param width Board's width.
	 * @param height Board's height.
	 * @param formatter Reference to a formatter.
	 * @param history History to initialize the board with.
	 */
	public Board(int width, int height, PlayerFormatterInterface formatter, List<HistoryEntry> history)
	{
		this(width, height, formatter);
		
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
	 * @brief Gets a reference to a board's formatter.
	 * @return Reference to the formatter.
	 */
	public PlayerFormatterInterface getFormatter()
	{
		return this.formatter;
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
					s.append(this.formatter.getPlayerRepresentation(this.board[x][y]));
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
	
	/**
	 * @brief Gets a cloned version of a board.
	 * @return Reference to the cloned version.
	 */
	public Object clone()
	{
		Board b = null;
		
		try
		{
			b = (Board) super.clone();
		}
		catch(CloneNotSupportedException e)
		{
			return null;
		}
		
		return b;
	}
}