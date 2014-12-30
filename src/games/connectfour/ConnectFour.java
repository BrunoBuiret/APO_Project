package games.connectfour;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;

import games.Board;
import games.Game;
import games.HistoryEntry;
import games.Player;
import games.Position;

/**
 * @author Bruno Buiret (11202344)
 * @version 1.1
 * @brief This class represents a game of connect four.
 */
public class ConnectFour extends Game
{
    /**
     * @brief Holds the serialization version number.
     */
    private static final long serialVersionUID = 8308672741825018981L;
    
    /**
     * @brief Creates a new connect four game.
     * @see games.Game.Game()
     */
    public ConnectFour()
    {
        super();
        this.name = "Connect Four";
        this.board = new Board(7, 6, new ConnectFourPlayerFormatter());
    }
    
    /**
     * @brief Plays a position on the board.
     * @see games.Game.play(Player, Position)
     * 
     * This method takes into account gravity by overriding the ordinate of the
     * given position and then placing the pawn.
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
            throw new InvalidParameterException(String.format("Column %d is already filled.", x));
        }
    }
    
    /**
     * @brief Checks if a player won a game of connect four.
     * @see games.Game.check(Player)
     */
    protected boolean check(Player player)
    {
        int i;
        
        // Check rows
        for(i = 0; i < 6; i++)
        {
            if(this.checkRow(player, i))
            {
                return true;
            }
        }
        
        // Check columns
        for(i = 0; i < 7; i++)
        {
            if(this.checkColumn(player, i))
            {
                return true;
            }
        }
        
        // Check diagonals
        for(int j = 5; j > 2; j--)
        {
            for(i = 0; i < 4; i++)
            {
                if(
                    // Ascending order
                    this.checkDiagonal(player, new int[]{i, j}, new int[]{i + 1, j - 1}, new int[]{i + 2, j - 2}, new int[]{i + 3, j - 3}) ||
                    // Descending order
                    this.checkDiagonal(player, new int[]{6 - i, j}, new int[]{6 - i - 1, j - 1}, new int[]{6 - i - 2, j - 2}, new int[]{6 - i - 3, j - 3}))
                {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    /**
     * @brief Helper method for the `check()` method.
     * @param p Reference to the player to check for.
     * @param row Index of the row.
     * @return `true` if the player won, `false` otherwise.
     */
    protected boolean checkRow(Player player, int row)
    {
        int consecutiveCells = 0;
        
        for(int column = 0; column < 7; column++)
        {
            if(this.board.getAt(column, row) != null)
            {
                if(this.board.getAt(column, row).equals(player))
                {
                    consecutiveCells++;
                }
                else
                {
                    consecutiveCells = 0;
                }
            }
            else
            {
                consecutiveCells = 0;
            }
            
            if(consecutiveCells >= 4)
            {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * @brief Helper method for the `check()` method.
     * @param p Reference to the player to check for.
     * @param column Index of the column
     * @return `true` if the player won, `false` otherwise.
     */
    protected boolean checkColumn(Player player, int column)
    {
        int consecutiveCells = 0;
        
        for(int row = 0; row < 6; row++)
        {
            if(this.board.getAt(column, row) != null)
            {
                if(this.board.getAt(column, row).equals(player))
                {
                    consecutiveCells++;
                }
                else
                {
                    consecutiveCells = 0;
                }
            }
            else
            {
                consecutiveCells = 0;
            }
            
            if(consecutiveCells >= 4)
            {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * @brief Helper method for the `check()` method.
     * @param player Reference to the player to check for.
     * @param firstCell First index is the abscissa of the first cell, second
     * index is the ordinate of the first cell.
     * @param secondCell First index is the abscissa of the second cell, second
     * index is the ordinate of the second cell.
     * @param thirdCell First index is the abscissa of the third cell, second
     * index is the ordinate of the third cell.
     * @param fourthCell First index is the abscissa of the fourth cell, second
     * index is the ordinate of the fourth cell.
     * @return `true` if the player won, `false` otherwise.
     */
    protected boolean checkDiagonal(Player player, int[] firstCell, int[] secondCell, int[] thirdCell, int[] fourthCell)
    {
        if(
            this.board.getAt(firstCell[0], firstCell[1]) != null && this.board.getAt(firstCell[0], firstCell[1]).equals(player)
            &&
            this.board.getAt(secondCell[0], secondCell[1]) != null && this.board.getAt(secondCell[0], secondCell[1]).equals(player)
            &&
            this.board.getAt(thirdCell[0], thirdCell[1]) != null && this.board.getAt(thirdCell[0], thirdCell[1]).equals(player)
            &&
            this.board.getAt(fourthCell[0], fourthCell[1]) != null && this.board.getAt(fourthCell[0], fourthCell[1]).equals(player)
        )
        {
            return true;
        }
        
        return false;
    }
    
    /**
     * @brief Prepares a game of connect four.
     * @see games.Game.prepare()
     */
    protected boolean prepare()
    {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        boolean keepScanning = false;
        int actionId = -1;
        
        // Asks the user what they want to do
        System.out.println("What do you want to do?");
        System.out.println(" 1. New Human vs Human");
        System.out.println(" 2. New Human vs Stupid AI");
        System.out.println(" 3. New Human vs Smart AI (Not available)");
        System.out.println(" 4. New Human vs Chuck Norris");
        System.out.println(" 5. Load a game");
        System.out.println(" 6. Return");
        List<Integer> possibleChoices = Arrays.asList(1, 2, 4, 5, 6);
        
        do
        {
            try
            {
                System.out.print("=> ");
                actionId = Integer.parseInt(r.readLine());
                keepScanning = !possibleChoices.contains(actionId);
            }
            catch(NumberFormatException e)
            {
                keepScanning = true;
            }
            catch(IOException e)
            {
                keepScanning = true;
            }
        }
        while(keepScanning);
        
        // Skip a line to keep the interface easily readable
        System.out.println();
        
        // Create the first player who's always human
        this.players.add(new HumanPlayer(1, this));
        
        switch(actionId)
        {
            case 1:
                this.players.add(new HumanPlayer(2, this));
            break;
            
            case 2:
                this.players.add(new StupidAI(2, this));
            break;
            
            case 3:
                this.players.add(new SmartAI(2, this));
            break;
            
            case 4:
                this.players.add(new ChuckNorris(2, this));
            break;
            
            case 5:
                return this.loadGame();
                
            case 6:
                return false;
        }
        
        return true;
    }
}