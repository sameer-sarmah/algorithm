package matrix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaxSumInMatrixTraversal {

	private static int[][] board = { { 1, 2, 4 }, 
									 { 9, 6, 2 }, 
									 { 23, 43, 30 } };
	public static void main(String[] args) {
		List<Integer> firstRowColSums = new ArrayList<Integer>();
		for(int col = 0;col< board[0].length;col++) {
			int sum =findMaxSum(board, 0, col, 0);
			firstRowColSums.add(sum);
			System.out.println(String.format("row: %d,column: %d,sum : %d",0,col,sum));
		}
		
		System.out.println(Collections.max(firstRowColSums));
	}

	private static int findMaxSum(int[][] board, int row, int column, int accumulated) {
		if (row < board.length) {
			List<Integer> childRowSums = new ArrayList<>();
			int currentValue = board[row][column];
			if (column - 1 >= 0) {
				childRowSums.add(findMaxSum(board,row + 1, column - 1,accumulated + currentValue));
			}
			
			childRowSums.add(findMaxSum(board,row + 1, column,accumulated + currentValue));

			if (column + 1 < board.length) {
				childRowSums.add(findMaxSum(board, row + 1, column + 1, accumulated + currentValue));
			}
			System.out.println(String.format("row: %d,column: %d,sum : %d",row,column,Collections.max(childRowSums)));
			return  Collections.max(childRowSums);
		}
		return accumulated;

	}
}
