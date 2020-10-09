package matrix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaxSumInMatrixTraversalOptimized {

	private static int[][] board = { { 1, 2, 4 }, 
									 { 9, 6, 2 }, 
									 { 23, 43, 30 } };
	private static Map<Cell,Integer> cache = new HashMap<>();

	public static void main(String[] args) {
		List<Integer> firstRowColSums = new ArrayList<Integer>();
		for(int col = 0;col< board[0].length;col++) {
			int sum =findMaxSum(board, 0, col, cache);
			firstRowColSums.add(sum);
			System.out.println(String.format("row: %d,column: %d,sum : %d",0,col,sum));
		}
		System.out.println(Collections.max(firstRowColSums));
	}

	private static int findMaxSumFromCache(int[][] board,int row, int column,Map<Cell,Integer> cache) {
		Cell childCell = new Cell(row , column);
		int childSum = 0;
		if(cache.get(childCell) == null) {
			childSum =findMaxSum(board, row, column,cache);
			cache.put(childCell, childSum);
		}
		else {
			childSum = cache.get(childCell);
			System.out.println(String.format("Retrieved from cache,%s : %d", childCell,childSum));
		}
		return childSum;
	}
	
	private static int findMaxSum(int[][] board, int row, int column,Map<Cell,Integer> cache) {
		if (row < board.length) {
			List<Integer> childRowSums = new ArrayList<>();
			int currentValue = board[row][column];
			if (column - 1 >= 0) {
				childRowSums.add(currentValue + findMaxSumFromCache(board,row + 1, column - 1,cache));
			}
			
			childRowSums.add(currentValue +findMaxSumFromCache(board,row + 1, column,cache));

			if (column + 1 < board.length) {
				childRowSums.add(currentValue + findMaxSumFromCache(board, row + 1, column + 1,cache));
			}
			System.out.println(String.format("row: %d,column: %d,sum : %d",row,column,Collections.max(childRowSums)));
			return  Collections.max(childRowSums);
		}
		return 0;

	}


	static class Cell {
		int row, column;

		public Cell(int row, int column) {
			super();
			this.row = row;
			this.column = column;
		}

		public int getRow() {
			return row;
		}

		public int getColumn() {
			return column;
		}

		@Override
		public String toString() {
			return "Cell [row=" + row + ", column=" + column + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + column;
			result = prime * result + row;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Cell other = (Cell) obj;
			if (column != other.column)
				return false;
			if (row != other.row)
				return false;
			return true;
		}

	}
}
