package games;

import java.security.InvalidParameterException;

/**
 * @author Bruno Buiret (11202344)
 * @version 1.0
 */
public class Position
{
	/**
	 * @brief Holds the position's abscissa. 
	 */
	protected final int x;
	
	/**
	 * @brief Holds the position's ordinate.
	 */
	protected final int y;
	
	/**
	 * @brief Creates a new position.
	 * @param x Position's abscissa.
	 * @param y Position's ordinate.
	 * @throws InvalidParameterException
	 */
	public Position(int x, int y) throws InvalidParameterException
	{
		if(x >= 0)
		{
			this.x = x;
		}
		else
		{
			throw new InvalidParameterException("A position's abscissa cannot be negative.");
		}
		
		if(y >= 0)
		{
			this.y = y;
		}
		else
		{
			throw new InvalidParameterException("A position's ordinate cannot be negative.");
		}
	}
	
	/**
	 * @brief Gets a position's abscissa.
	 * @return Position's abscissa.
	 */
	public int getX()
	{
		return this.x;
	}
	
	/**
	 * @brief Gets a position's ordinate.
	 * @return Position's ordinate.
	 */
	public int getY()
	{
		return this.y;
	}
	
	/**
	 * @brief Parses a string to create a new position.
	 * @param s String to parse.
	 * @return Reference to the new position or `null` if none could be created.
	 */
	public static Position parse(String s)
	{
		return new Position(0, 0);
	}
}