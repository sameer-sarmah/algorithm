package backtracking;

import java.util.Arrays;

public class NQueen {
	int N = 4;
	int[][] board = new int[N][N];

	public static void main(String[] args) {
		NQueen nq = new NQueen();
		if (nq.solveNQueen(0) == true)
			nq.display();
	}

	{
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board.length; j++)
				board[i][j] = 0;
	}

	private boolean isSafe(int row, int col) {
		if (row >= 0 && row < board.length && col >= 0 && col < board[row].length)
			return true;
		else
			return false;
	}

	private boolean placeQueen(int row, int col) {
		for (int i = 0; i < board.length; i++) {
			if (board[i][col] == 1)
				return false;
		}
		for (int i = 0; i < board.length; i++) {
			if (board[row][i] == 1)
				return false;
		}
		int i = row, j = col;
		while (i >= 0 && j >= 0) {
			if (board[i][j] == 1)
				return false;
			--i;
			--j;
		}
		i = row;
		j = col;
		/*
		 * As we are placing queens in a column wise manner we know that there won't we any 
		 * already placed queen on right side of column
		 * */
		while (i < board.length && j >= 0) {
			if (board[i][j] == 1)
				return false;
			++i;
			--j;
		}
		return true;
	}

	public boolean solveNQueen(int col) {
		if (col == board.length)
			return true;

		for (int row = 0; row < board.length; row++) {
			//try each row for a column
			if (isSafe(row, col) && placeQueen(row, col)) {
				board[row][col] = 1;
				if (solveNQueen(col + 1) == true)
					return true;
				board[row][col] = 0;
			}
		}

		return false;
	}

	public void display() {
		for (int i = 0; i < board.length; i++)
			System.out.println(Arrays.toString(board[i]));
	}

}