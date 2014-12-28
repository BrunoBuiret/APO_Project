package games;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.security.InvalidParameterException;
import java.util.ArrayList;
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
	private static final long serialVersionUID = 1L;
	
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
	 * This method must ask the user if they want to create a new game, if so, against
	 * what type of adversary, load an existing game or quit.
	 */
	protected abstract boolean prepare();
	
	/**
	 * @brief Represents the main loop of the game.
	 * @todo Add the possibility of saving.
	 * 
	 * This method handles if the user wants to keep playing, save the game or quit by first
	 * asking them that. Then, if they want to play, 
	 */
	protected void loop()
	{
		boolean keepLooping = true;
		int playerIndex = 0;
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		
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
					// The current player is human, so ask them what they want to do
					boolean keepScanning = false;
					int actionId = -1;
					
					System.out.println("What do you want to do?");
					System.out.println(" 1. Play");
					System.out.println(" 2. Save");
					System.out.println(" 3. Quit");
					
					do
					{
						try
						{
							System.out.print("=> ");
							actionId = Integer.parseInt(r.readLine());
							keepScanning = actionId != 1 && actionId != 2 && actionId != 3;
						}
						catch (NumberFormatException e)
						{
							keepScanning = true;
						}
						catch (IOException e)
						{
							keepScanning = true;
						}
					}
					while(keepScanning);
					
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
								keepPlaying = true;
							}
						break;
						
						case 2:
							// Save the game
							throw new RuntimeException("Saving hasn't been implemented yet.");
						
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
				keepLooping = false;
			}
			// Or if it game is a draw
			else if(this.history.size() == this.board.getHeight() * this.board.getWidth())
			{
				System.out.print(this.board);
				System.out.println("Nobody won.");
				keepLooping = false;
			}
			
			// Change player
			playerIndex = ++playerIndex % this.players.size();
		}
		
		try
		{
			r.close();
		}
		catch (IOException e)
		{
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