import java.io.*;
import java.util.Scanner;

/**
A Sudoku Solver
@author Anish Seth
@version 12.13.15
*/
public class SudokuSolver
{
	/**
	Solves board
	@param String filename of the board
	@return solved game board
	*/
	public static GameBoard solver(String filename)
	{
		Stack<GameBoard> stack = new LinkedList<GameBoard>();
		GameBoard start = new GameBoard(filename);
		stack.push(start);
		boolean isSolved = false;
		while(isSolved == false)
		{
			GameBoard check = stack.pop();
			if(check.solved() == true)
			{
				isSolved = true;
				return check;
			}
			int[] mostC = check.getMostConstrained();
			System.out.println(mostC[0] + " " + mostC[1]);
			int[] possiblemostC = check.getMostConstrainedPossibilities(mostC);
			int row = mostC[0];
			int col = mostC[1];
			/**
			Finds most constrained space
			Creates new stack for each possible space that is most constrained
			adds the number to the board and adds board to stack
			*/
			for (int i = 0; i < possiblemostC.length; i++)
			{
				GameBoard toAdd = new GameBoard(check);
				toAdd.place(row,col,possiblemostC[i]);
				stack.push(toAdd);
			}
		}
		return null;
	}	
}