/**
 * GameBoard class for the Sudoku Solver
 * @author Anish Seth
 * @version 11-30-15
 */
import java.io.*;
import java.util.Scanner;
public class GameBoard
{
	private int[][] board;
	public GameBoard()
	{

	}
	public static String ReadCSV()
	{	
		String pathname = "Sheet1.csv";
		File file = new File(pathname);	
		Scanner input = null;
		String s = "";
		try
		{
			input = new Scanner(file);
		}
		catch (FileNotFoundException ex)
		{
			System.out.println(" Cannot open " + pathname );
			System.exit(1);
		}
		
		while( input.hasNextLine() )
		{
			s += input.nextLine() + "\n";
		}
		return s;
	}
	void place(int r, int c, int n) // place numeral n at position (r,c) 
	{
		board[r][c] = n;
	}
	void print() // print out the board
	{
		String s = "------------------\n";
		for(int r = 0; r < board.length; r++)
		{
			s += "|";
			for(int c = 0; c< board[r].length; c++)
			{
				s += board[r][c] + "|";
			}
			s += "------------------\n";
		}
		System.out.println(s);
	}
	int get(int r, int c) // return the numeral at position (r,c) 
	{
		return board[r][c];
	}
	void remove(int r, int c) // remove the numeral at position (r,c) 
	{
		board[r][c] = 0;
	}
	boolean canPlace(int r, int c, int n) // true if the board would allow placing n at (r,c) 
	{
		return board[r][c] == 0;
	}
	boolean solved() // true if there are no blank spots on the board
	{
		for(int r = 0; r < board.length; r++)
		{
			for(int c = 0; c< board[r].length; c++)
			{
				if(board[r][c] == 0)
					return false;
			}
		}
		return true;
	}

}