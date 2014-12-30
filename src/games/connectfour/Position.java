package games.connectfour;

import java.security.InvalidParameterException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Bruno Buiret (11202344)
 * @version 1.0
 * @brief Overrides classic position class to redefine `parse()` so that it only captures columns.
 */
public class Position extends games.Position
{
    /**
     * @brief Holds the serialization version number.
     */
    private static final long serialVersionUID = 6116516082477823797L;
    /**
	 * @brief Holds the column's pattern.
	 */
	protected final static Pattern columnMatcher = Pattern.compile("^Column\\(([0-9]+)\\)$");
	
	/**
	 * @brief Creates a new position.
	 * @see games.Position.Position(int, int)
	 */
	public Position(int x, int y) throws InvalidParameterException
	{
		super(x, y);
	}
	
	/**
	 * @brief Overrides the superclass method to ignore line number.
	 * @see games.Position.parse(String)
	 */
	public static Position parse(String s)
	{
		Matcher m = Position.columnMatcher.matcher(s);
		return m.matches() ? new Position(Integer.parseInt(m.group(1)), 0) : null;
	}
}