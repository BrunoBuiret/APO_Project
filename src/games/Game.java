package games;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Bruno Buiret (11202344)
 * @version 1.1
 * @brief This class can be used to hold the logic of a board game.
 */
public abstract class Game implements Serializable
{
    /**
     * @brief Holds the serialization version number.
     */
    private static final long serialVersionUID = 3131043330536373089L;

    /**
     * @brief Holds the name of the game.
     */
    protected String name;
    
    /**
     * @brief Holds a list of players.
     */
    protected List<Player> players;
    
    /**
     * @brief Holds a reference to the board.
     */
    protected Board board;
    
    /**
     * @brief Holds a list of every played action.
     */
    protected List<HistoryEntry> history;
    
    /**
     * @brief Creates a new game.
     */
    public Game()
    {
        this.name = null;
        this.players = new ArrayList<Player>();
        this.board = null;
        this.history = new ArrayList<HistoryEntry>();
    }
    
    /**
     * @brief Prepares the game and then launches it if the user hasn't quit.
     */
    public void run()
    {
        System.out.println(String.format("==[%s]==", this.name));
        
        if(this.prepare())
        {
            this.loop();
        }
    }
    
    /**
     * @brief Adds a move to a game.
     * @param player Reference to the player.
     * @param position Reference to the position.
     * 
     * This method must be implemented by any class representing a game. It will
     * contain the logic of an action being played.
     */
    protected abstract void play(Player player, Position position);
    
    /**
     * @brief Cancels the last played action of a game.
     */
    protected void cancel()
    {
        if(this.history.size() > 0)
        {
            HistoryEntry e = this.history.remove(this.history.size() - 1);
            this.board.setAt(e.getPosition(), null);
        }
    }
    
    /**
     * @brief Checks if a player won a game.
     * @param player Reference to the player.
     * @return `true` if the player has won, `false` otherwise.
     */
    protected abstract boolean check(Player player);
    
    /**
     * @brief Prepares a game.
     * @return `true` if a game was created, `false` if the user wants to quit.
     * 
     * This method must ask the user if they want to create a new game, if so,
     * against what type of adversary, load an existing game or quit.
     */
    protected abstract boolean prepare();
    
    /**
     * @brief Represents the main loop of the game.
     * 
     * This method handles if the user wants to keep playing, save the game or
     * quit by first asking them that. Then, if they want to play,
     */
    protected void loop()
    {
        boolean keepLooping = true;
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        int playerIndex;
        
        if(this.history.size() == 0)
        {
            playerIndex = 0;
        }
        else
        {
            playerIndex = (this.players.indexOf(this.history.get(this.history.size() - 1).getPlayer()) + 1) % this.players.size();
        }
        
        while(keepLooping)
        {
            // Display the board
            System.out.print(this.board);
            
            // Let the current user do something
            boolean keepPlaying = false;
            
            do
            {
                if(!(this.players.get(playerIndex) instanceof AIPlayer))
                {
                    // The current player is human, so ask them what they want
                    // to do
                    boolean keepScanning = false;
                    int actionId = -1;
                    
                    System.out.println(String.format("%s: What do you want to do?", this.players.get(playerIndex)));
                    System.out.println(" 1. Play");
                    System.out.println(" 2. Save");
                    System.out.println(" 3. Quit");
                    List<Integer> possibleChoices = Arrays.asList(1, 2, 3);
                    
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
                    
                    switch(actionId)
                    {
                        case 1:
                            // Play
                            Position position = this.players.get(playerIndex).getNextPosition();
                            
                            if(position != null)
                            {
                                try
                                {
                                    // Try playing this position
                                    this.play(this.players.get(playerIndex), position);
                                    
                                    // Tell the user what position was played
                                    System.out.println(String.format("%s played %s.", this.players.get(playerIndex), position));
                                    System.out.println();
                                    
                                    // Stop looping
                                    keepPlaying = false;
                                }
                                catch(InvalidParameterException e)
                                {
                                    System.out.println(e.getMessage());
                                    keepPlaying = true;
                                }
                            }
                            else
                            {
                                System.err.println("Invalid move.");
                                keepPlaying = true;
                            }
                        break;
                        
                        case 2:
                            // Save the game
                            keepScanning = false;
                            try
                            {
                                // Ask the user for a filename
                                String filename = this.askFilename("Enter a filename", false);
                                
                                // Save the current state of the game
                                this.save(filename);
                                
                                // Inform the user
                                System.out.println(String.format("Game was saved in \"%s\".", (new File(filename).getAbsolutePath())));
                                System.out.println();
                                
                                // And keep playing
                                keepPlaying = true;
                            }
                            catch(IOException e)
                            {
                                System.out.println(String.format("Couldn't save the game: %s.", e));
                                keepPlaying = true;
                            }
                        break;
                        
                        case 3:
                            // Quit the game
                            return;
                    }
                }
                else
                {
                    // The current player is an AI
                    Position position = this.players.get(playerIndex).getNextPosition();
                    
                    if(position != null)
                    {
                        try
                        {
                            // Try playing this position
                            this.play(this.players.get(playerIndex), position);
                            
                            // Tell the user what position was played
                            System.out.println(String.format("%s played %s.", this.players.get(playerIndex), position));
                            System.out.println();
                            
                            // Stop looping
                            keepPlaying = false;
                        }
                        catch(InvalidParameterException e)
                        {
                            keepPlaying = true;
                        }
                    }
                    else
                    {
                        keepPlaying = true;
                    }
                }
            }
            while(keepPlaying);
            
            // Check if the player won the game
            if(this.check(this.players.get(playerIndex)))
            {
                System.out.print(this.board);
                System.out.println(String.format("%s has won.", this.players.get(playerIndex)));
                System.out.println();
                keepLooping = false;
            }
            // Or if it game is a draw
            else if(this.history.size() == this.board.getHeight() * this.board.getWidth())
            {
                System.out.print(this.board);
                System.out.println("Nobody won.");
                System.out.println();
                keepLooping = false;
            }
            
            // Change player
            playerIndex = ++playerIndex % this.players.size();
        }
    }
    
    /**
     * @brief Utility method to ask the user for a filename.
     * @param message Message to display to the user.
     * @param existingFile Should the file already exists.
     * @return Filename.
     */
    protected String askFilename(String message, boolean existingFile)
    {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        boolean keepScanning = false;
        String filename = null;
        File file = null;
        
        do
        {
            System.out.print(String.format("%s: ", message));
            
            try
            {
                filename = r.readLine();
                file = new File(filename);
                
                if(existingFile)
                {
                    keepScanning = !(file.exists() && file.isFile());
                }
                else
                {
                    keepScanning = !((file.exists() && file.isFile()) || !file.exists());
                }
            }
            catch(IOException e)
            {
                keepScanning = true;
            }
        }
        while(keepScanning);
        
        return filename;
    }
    
    /**
     * @brief Superclass method to avoid code redundancy.
     * @return `true` if a game was loaded, `false` otherwise.
     */
    protected boolean loadGame()
    {
        try
        {
            // Ask the user for a filename
            String filename = this.askFilename("Enter an existing filename", true);
            
            // Load the game
            this.load(filename);
            
            // Inform the user
            System.out.println(String.format("Loaded \"%s\".", (new File(filename)).getAbsolutePath()));
            System.out.println();
        }
        catch(ClassNotFoundException e)
        {
            System.out.println(String.format("This file belongs to an outdated version of %s.", this.name));
            return false;
        }
        catch(IOException | GameSaveMismatchException e)
        {
            System.err.println(String.format("Couldn't load game: %s.", e.getMessage()));
            return false;
        }
        
        return true;
    }
    
    /**
     * @brief Saves the current state of a game.
     * @param filename File name.
     * @throws IOException Thrown when `filename` isn't valid, when the file
     * cannot be created or when the stream cannot be written.
     * @warning If a file designated by `filename` already exists, it will be
     * overwritten.
     */
    protected void save(String filename) throws IOException
    {
        try
        {
            // Open a stream
            ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(filename));
            
            // Save the class name so as not to mistake games
            stream.writeObject(this.getClass().getName());
            // Save the name
            stream.writeObject(this.name);
            // Save the list of players
            stream.writeObject(this.players);
            // Save the board
            stream.writeObject(this.board);
            // Save the history
            stream.writeObject(this.history);
            
            // Flush every data left and then close
            stream.flush();
            stream.close();
        }
        catch(IOException e)
        {
            throw e;
        }
    }
    
    /**
     * @brief Loads a game.
     * @param filename File name.
     * @throws IOException Thrown when the file doesn't exist or when the stream
     * cannot be read.
     * @throws ClassNotFoundException Thrown when the serialized class doesn't
     * match the existing definition.
     * @throws GameSaveMismatchException Thrown when the save file doesn't match
     * the game.
     */
    protected void load(String filename) throws IOException, ClassNotFoundException, GameSaveMismatchException
    {
        try
        {
            // Open a stream
            ObjectInputStream stream = new ObjectInputStream(new FileInputStream(filename));
            
            // Load the class name
            String className = (String) stream.readObject();
            
            if(className.equals(this.getClass().getName()))
            {
                // Load the name
                this.name = (String) stream.readObject();
                // Load the list of players
                this.players = (List<Player>) stream.readObject();
                // Load the board
                this.board = (Board) stream.readObject();
                // Load the history
                this.history = (List<HistoryEntry>) stream.readObject();
            }
            else
            {
                stream.close();
                throw new GameSaveMismatchException(String.format("This save isn't for %s but %s", className, this.getClass().getName()));
            }
            
            // Close the stream
            stream.close();
        }
        catch(IOException e)
        {
            throw e;
        }
        catch(ClassNotFoundException e)
        {
            throw e;
        }
    }
    
    /**
     * @brief Gets the name of a game.
     * @return Game's name.
     */
    public String getName()
    {
        return this.name;
    }
    
    /**
     * @brief Gets the board of a game.
     * @return Reference to the board.
     */
    public Board getBoard()
    {
        return this.board;
    }
    
    /**
     * @brief Gets a player from a game.
     * @param index Index of the player in the list.
     * @return Reference to the wanted player.
     * @throws InvalidParameterException
     */
    public Player getPlayer(int index) throws InvalidParameterException
    {
        if(index < this.players.size())
        {
            return this.players.get(index);
        }
        
        throw new InvalidParameterException("There are no players");
    }
    
    /**
     * @brief Gets the history of a game.
     * @return Reference to the history.
     */
    public List<HistoryEntry> getHistory()
    {
        return this.history;
    }
}