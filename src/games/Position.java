package games;

import java.io.Serializable;
import java.security.InvalidParameterException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Bruno Buiret (11202344)
 * @version 1.0
 * @brief This class holds a 2D position.
 */
public class Position implements Serializable
{
    /**
     * @brief Holds the serialization version number.
     */
    private static final long serialVersionUID = -5492464190344807110L;

    /**
     * @brief Holds the position's abscissa.
     */
    protected final int x;
    
    /**
     * @brief Holds the position's ordinate.
     */
    protected final int y;
    
    /**
     * @brief Holds the position's pattern.
     */
    protected final static Pattern positionMatcher = Pattern.compile("^Position\\(([0-9]+),\\s*([0-9]+)\\)$");
    
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
        Matcher m = Position.positionMatcher.matcher(s);
        return m.matches() ? new Position(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2))) : null;
    }
    
    /**
     * @brief Gets a string representation of a position.
     * @return Position's string representation.
     */
    public String toString()
    {
        return String.format("(%d ; %d)", this.x, this.y);
    }
}