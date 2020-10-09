package matrix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class MaxSumPathInMatrixTraversal {

	private static int[][] board = { { 1, 2, 4 }, 
									 { 9, 6, 2 }, 
									 { 23, 43, 30 } };
	public static void main(String[] args) {
		List<List<Integer>> paths = new ArrayList<>();
		for(int col = 0;col< board[0].length;col++) {
			Stack<Integer> path =new Stack<>();
			findMaxSum(board, 0, col, paths,path);
		}
		List<Integer> maxSumPath = null;
		int maxSum = 0;
		for(List<Integer> path: paths) {
			int localSum = path.stream().reduce((x,y) -> x+y).get();
			if(localSum > maxSum) {
				maxSum = localSum;
				maxSumPath = path;
			}
		}
		System.out.println(maxSumPath);
		System.out.println(maxSum);
		

	}

	private static void findMaxSum(int[][] board, int row, int column, List<List<Integer>> paths,Stack<Integer> path) {
		if (row < board.length) {
			int currentValue = board[row][column];
			path.add(currentValue);
			if (column - 1 >= 0) {
				findMaxSum(board,row + 1, column - 1,paths,path);
			}
			
			findMaxSum(board,row + 1, column,paths,path);

			if (column + 1 < board.length) {
				findMaxSum(board, row + 1, column + 1, paths,path);
			}
			path.pop();
		}
		else
			paths.add(path.stream().collect(Collectors.toList()));
		

	}
}
