/**

 * GameBoard class for the Sudoku Solver
 * @author Anish Seth
 * @version 11-30-15
 */
import java.io.*;
import java.util.Scanner;
public class GameBoard
{
	public Integer [][] board = new Integer[9][9];
	/**
	 * Constructor
	 * @param board to be played
	 */
	public Gameboard(String filename)
	{
		Integer[] number = readCSV(filename);
		int count = 0;
		for(int r = 0; r < board.length; r++)
		{
			for(int c = 0; c < board[r].length; c++)
			{
				board[r][c] = number[count];
				count++;
			}
		}		
	}
	/**
	 * Copy Constructor
	 * @param Gameboard
	 */
	public Gameboard(Gameboard game)
	{
		for(int r = 0; r < game.getBoard().length; r++)
		{
			for(int c = 0; c < game.getBoard()[r].length; c++)
			{	
				board[r][c] = game.getBoard()[r][c];
			}
		}
	}
	public static String ReadCSV()
	{	
		String pathname = "SudokuBoard.csv";
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
	/**
	 * Accessor of board
	 * @return board
	 */
	public Integer[][] getBoard()
	{
		return board;	
	}
	public void place(int r, int c, int n) // place numeral n at position (r,c) 
	{
		board[r][c] = n;
	}
	public void print() // print out the board
	{
		String s = "------------------------------------\n";
		for(int r = 0; r < 9; r++)
		{
			s += "| ";
			for(int c = 0; c < 9; c++)
			{
				s += board[r][c] + " | ";
			}
			s += "\n------------------------------------\n";
		}
		System.out.println(s);
	}
	/**
	 * return the numeral at position (r,c) 
	 */
	public int get(int r, int c) 
	{
		return board[r][c];
	}
	/**
	 * remove the numeral at position (r,c)
	 */
	public void remove(int r, int c) 
	{
		board[r][c] = 0;
	}
	/**
	 * true if the board would allow placing n at (r,c)
	 * @return boolean whether you can place n at r,c
	 */
	public boolean canPlace(int r, int c, int n)
	{
		if (board[r][c] != 0)
			return false;
		for (int i = 0; i < 9; i++)  
		{
			if (board[r][i] == n || board[i][c] == n)
				return false;
		}
		for (int i = 0; i < 3; i++)
		{
			for (int x = 0; x < 3; x++)
			{
				if (board[i+((r/3)*3)][x+((c/3)*3)] == n)
					return false;
			}
		}
		return true;
	}
	/**
	 * true if there are no blank spots on the board
	 */
	public boolean solved() 
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
	/**
	* Returns the most constrained spot on the board
	* @return Integer [] the row and column of the most constrained spot
	*/
	public Integer[] getMostConstrained()
	{
		Integer[] constrained = new Integer[2];
		int low = 9;
		
		int curr;

		for(int r = 0; r < board.length; r++)
		{
			for(int c = 0; c < board[r].length; c++)
			{	
				if(board[r][c] == 0)
				{
					curr = 0; 
					for(int n = 1; n <= 9; n++) 
					{
						if (canPlace(r,c,n) == true)
						{
							curr++;				
						}
					}
					if(curr < low)
					{
						constrained[0] = r; //row will always be first int
						constrained[1] = c; //col will always be second int
						low = curr;
					}
				}
			}
		}	
		return constrained;
	}
	/**
	 * Returns most constrained spot on board's possiblities
	 */
	public Integer[] getMostConstrainedPossibilities(Integer[] rowcol)
	{
	  	ArrayList<Integer> possible = new ArrayList<Integer>();
	  
		int r = rc[0];
		int c = rc[1];
		
		for(int n = 1; n <= 9; n++) //check numbers 1-9
		{
			if (canPlace(r,c,n) == true)
			{
				possible.add(n);				
			}
		}
		Integer[] possiblev = new Integer[possible.size()];
		
		for (int i = 0; i< possible.size(); i++)
		{
			possiblev[i] = possible.get(i);
		}
		return possiblev;
	}
}