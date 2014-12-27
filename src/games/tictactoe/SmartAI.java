package games.tictactoe;

import java.util.ArrayList;

import games.Board;
import games.AIPlayer;
import games.Player;
import games.Position;

/**
 * @author Bruno Buiret (11202344)
 * @version 1.0
 * @brief This class represents an AI that "thinks" in order to win a game
 * of tic tac toe.
 */
public class SmartAI extends AIPlayer
{
	/**
	 * @brief Holds the serialization version number.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * @brief Creates a new smart AI for the tic tac toe.
	 * @see games.Player.Player(int, Game)
	 */
	public SmartAI(int number, TicTacToe game)
	{
		super(number, game);
	}
	
	/**
	 * @brief Determines the next best position to play.
	 * @see games.Player.getNextPosition()
	 */
	public Position getNextPosition()
	{
		int[] minimaxResult = this.minimax(this, (Board) this.game.getBoard().clone(), 2);
		return minimaxResult[1] != -1 && minimaxResult[2] != -1 ? new Position(minimaxResult[1], minimaxResult[2]) : null;
	}
	
	/**
	 * @brief Determines the best next move thanks to the minimax algorithm.
	 * @param player Reference to the current "player".
	 * @param board Reference to the board.
	 * @param depth Current depth of the algorithm.
	 * @return Array containing the best obtained score while searching, and then
	 * the best next column and row to play.
	 * @see http://www.ntu.edu.sg/home/ehchua/programming/java/JavaGame_TicTacToe_AI.html
	 */
	protected int[] minimax(Player player, Board board, int depth)
	{
		// First, determine every available moves by going through the board
		ArrayList<Position> availableMoves = new ArrayList<Position>();
		
		for(int y = 0; y < board.getHeight(); y++)
		{
			for(int x = 0; x < board.getWidth(); x++)
			{
				if(board.getAt(x, y) == null)
				{
					availableMoves.add(new Position(x, y));
				}
			}
		}
		
		// Then, initialize vars to memorize the best next position to play
		Player oppositePlayer = this.game.getPlayer((this.number + 1) % 2);
		int bestScore = player.equals(this) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
		int currentScore;
		int bestColumn = -1;
		int bestRow = -1;
		
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
				board.setAt(p, player);
				
				if(player.equals(this))
				{
					// Current player is the AI, it's the maximizing player
					currentScore = this.minimax(oppositePlayer, board, depth - 1)[0];
					
					if(currentScore > bestScore)
					{
						bestScore = currentScore;
						bestColumn = p.getX();
						bestRow = p.getY();
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
						bestRow = p.getY();
					}
				}
				
				// Undo the move
				board.setAt(p, null);
			}
		}
		
		return new int[]{bestScore, bestColumn, bestRow};
	}
	
	/**
	 * @brief Evaluates the current score of a board for the minimax algorithm.
	 * @param b Reference to the board.
	 * @return Current's board's score.
	 * 
	 * This is the heuristic evaluation function of the board. The returned value
	 * depends on the current state of the board. It associates a score to a 3-in-a-line,
	 * 2-in-a-line and 1-in-a-line for a sole player, then sums it up.
	 * 
	 *  * +100 for each 3-in-a-line for the AI
	 *  * +10 for each 2-in-a-line for the AI
	 *  * +1 for each 1-in-a-line for the AI
	 *  * -100 for each 3-in-a-line for the human player
	 *  * -10 for each 2-in-a-line for the human player
	 *  * -1 for each 1-in-a-line for the human player
	 *  
	 *  It will then be used to determine the best position to play.
	 */
	protected int evaluateScore(Board b)
	{
		int score = 0;
		
		// Rows
		score += evaluateCombination(b, 0, 0, 0, 1, 0, 2);
		score += evaluateCombination(b, 1, 0, 1, 1, 1, 2);
		score += evaluateCombination(b, 2, 0, 2, 1, 2, 2);
		// Columns
		score += evaluateCombination(b, 0, 0, 1, 0, 2, 0);
		score += evaluateCombination(b, 0, 1, 1, 1, 2, 1);
		score += evaluateCombination(b, 0, 2, 1, 2, 2, 2);
		// Diagonals
		score += evaluateCombination(b, 0, 0, 1, 1, 2, 2);
		score += evaluateCombination(b, 0, 2, 1, 1, 2, 0);
		
		return score;
	}
	
	/**
	 * @brief Determines the score of a three cells combination.
	 * @param b Reference to the board.
	 * @param x1 Abscissa of the first cell.
	 * @param y1 Ordinate of the first cell.
	 * @param x2 Abscissa of the second cell.
	 * @param y2 Ordinate of the second cell.
	 * @param x3 Abscissa of the third cell.
	 * @param y3 Ordinate of the third cell.
	 * @return Three cells combination's score.
	 */
	protected int evaluateCombination(Board b, int x1, int y1, int x2, int y2, int x3, int y3)
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
				if(score == 1)
				{
					score = 10;
				}
				else if(score == -1)
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
				if(score == -1)
				{
					score = -10;
				}
				else if(score == 1)
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
		
		return score;
	}
}