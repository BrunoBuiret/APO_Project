package games.connectfour;

import java.util.ArrayList;
import games.AIPlayer;
import games.Board;
import games.Player;
import games.Position;

/**
 * @author Bruno Buiret (11202344)
 * @version 1.0
 * @brief This class represents an AI that "thinks" in order to win a game of
 * connect four.
 */
public class SmartAI extends AIPlayer
{
	/**
	 * @brief Holds the serialization version number.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * @brief Creates a new smart AI for the connect four.
	 * @see games.Player.Player(int, Game)
	 */
	public SmartAI(int number, ConnectFour game)
	{
		super(number, game);
	}
	
	/**
	 * @brief Determines the next best position to play.
	 * @see games.Player.getNextPosition()
	 * @todo Implement this method.
	 */
	public Position getNextPosition()
	{
		int[] minimaxResult = this.minimax(this, (Board) this.game.getBoard().clone(), 4);
		return minimaxResult[1] != -1 ? new Position(minimaxResult[1], 0) : null;
	}
	
	/**
	 * @brief Determines the best next move thanks to an adaptation of the minimax algorithm.
	 * @param player Reference to the current "player".
	 * @param board Reference to the board.
	 * @param depth Current depth of the algorithm.
	 * @return Array containing the best obtained score while searching, and then
	 * the best next column to play.
	 * @see games.tictactoe.minimax(Player, Board, int)
	 */
	protected int[] minimax(Player player, Board board, int depth)
	{
		// First, determine every available moves by going through the columns
		ArrayList<Position> availableMoves = new ArrayList<Position>();
		
		for(int c = 0; c < board.getWidth(); c++)
		{
			if(board.getAt(c, 0) == null)
			{
				// This column isn't filled, determine the lowest line
				int l = 0;
				
				while(l + 1 < board.getHeight() && board.getAt(c, l + 1) == null)
				{
					l++;
				}
				
				availableMoves.add(new Position(c, l));
			}
		}
		
		// Then, initialize vars to memorize the best next position
		Player oppositePlayer = this.game.getPlayer((this.number + 1) % 2);
		int bestScore = player.equals(this) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
		int currentScore;
		int bestColumn = -1;
		
		if(availableMoves.isEmpty() || depth == 0)
		{
			// We've reached the bottom of the tree or there is no other available move
			bestScore = this.evaluateScore(board);
		}
		else
		{
			// Determine the better position to play
			for(Position p : availableMoves)
			{
				// Try this move for the current "player"
				board.setAt(p,  player);
				
				if(player.equals(this))
				{
					// Current player is the AI, it's the maximizing player
					currentScore = this.minimax(oppositePlayer, board, depth - 1)[0];
					
					if(currentScore > bestScore)
					{
						bestScore = currentScore;
						bestColumn = p.getX();
					}
				}
				else
				{
					// Current player is the user, it's the minimizing player
					currentScore = this.minimax(this, board, depth - 1)[0];
					
					if(currentScore < bestScore)
					{
						bestScore = currentScore;
						bestColumn = p.getX();
					}
				}
				
				// Undo the move
				board.setAt(p, null);
			}
		}
		
		return new int[]{bestScore, bestColumn};
	}
	
	/**
	 * @brief Evaluates the current score of a board for the minimax algorithm.
	 * @param b Reference to the board.
	 * @return Current's board's score.
	 * @todo Add the diagonals
	 * 
	 * This is the heuristic evaluation function of the board. The returned value
	 * depends on the current state of the board. It associates a score to a 4-in-a-line,
	 * 3-in-a-line, 2-in-a-line and 1-in-a-line for a sole player, then sums it up.
	 * 
	 *  * +1000 for each 4-in-a-line for the AI
	 *  * +100 for each 3-in-a-line for the AI
	 *  * +10 for each 2-in-a-line for the AI
	 *  * +1 for each 1-in-a-line for the AI
	 *  * -1000 for each 4-in-a-line for the AI
	 *  * -100 for each 3-in-a-line for the human player
	 *  * -10 for each 2-in-a-line for the human player
	 *  * -1 for each 1-in-a-line for the human player
	 *  
	 *  It will then be used to determine the best position to play.
	 */
	protected int evaluateScore(Board b)
	{
		/*
		int score = 0;
		int r, c;
		
		// Rows
		for(r = 0; r < 6; r++)
		{
			for(c = 0; c < 4; c++)
			{
				score += this.evaluateCombination(
					b,
					c, r,
					c + 1, r,
					c + 2, r,
					c + 3, r
				);
			}
		}
		
		// Columns
		for(c = 0; c < 7; c++)
		{
			for(r = 5; r > 2; r--)
			{
				score += this.evaluateCombination(
					b,
					c, r,
					c, r - 1,
					c, r - 2,
					c, r - 3
				);
			}
		}
		
		// Diagonals
		*/
		int score = 0;
		int i;
		
		// Horizontals
		for(i = 0; i < 7; i++)
		{
			score += evaluateRow(b, i);
		}
		
		// Verticals
		for(i = 0; i < 6; i++)
		{
			score += evaluateColumn(b, i);
		}
		
		return score;
	}
	
	protected int evaluateCombination(Board b, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4)
	{
		int score = 0;
		
		// First cell
		if(b.getAt(x1, y1) != null)
		{
			score = b.getAt(x1, y1).equals(this) ? 1 : -1;
		}
		
		// Second cell
		if(b.getAt(x2, y2) != null)
		{
			if(b.getAt(x2, y2).equals(this))
			{
				if(score > 0)
				{
					score *= 10;
				}
				else if(score < 0)
				{
					return 0;
				}
				else
				{
					score = 1;
				}
			}
			else
			{
				if(score < 0)
				{
					score *= 10;
				}
				else if(score > 0)
				{
					return 0;
				}
				else
				{
					score = -1;
				}
			}
		}
		
		// Third cell
		if(b.getAt(x3, y3) != null)
		{
			if(b.getAt(x3, y3).equals(this))
			{
				if(score > 0)
				{
					score *= 10;
				}
				else if(score < 0)
				{
					return 0;
				}
				else
				{
					score = 1;
				}
			}
			else
			{
				if(score < 0)
				{
					score *= 10;
				}
				else if(score > 0)
				{
					return 0;
				}
				else
				{
					score = -1;
				}
			}
		}
		
		// Fourth cell
		if(b.getAt(x4, y4) != null)
		{
			if(b.getAt(x4, y4).equals(this))
			{
				if(score > 0)
				{
					score *= 10;
				}
				else if(score < 0)
				{
					return 0;
				}
				else
				{
					score = 1;
				}
			}
			else
			{
				if(score < 0)
				{
					score *= 10;
				}
				else if(score > 0)
				{
					return 0;
				}
				else
				{
					score = -1;
				}
			}
		}
		
		return score;
	}
	
	protected int evaluateRow(Board b, int row)
	{
		int score = 0;
		return score;
	}
	
	protected int evaluateColumn(Board b, int column)
	{
		int score = 0;
		return score;
	}
}