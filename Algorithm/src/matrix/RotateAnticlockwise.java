package matrix;

import java.util.Arrays;

import matrix.BoundaryTraversal.Traversal;

public class RotateAnticlockwise {
	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 3, 4 }, { 10, 11, 12, 5 }, { 9, 8, 7, 6 } };
		int shift = 1;
		/*
		 * shift = 2 3 4 5 6 2 11 12 7 1 10 9 8
		 * 
		 */
		int[][] shiftedMatrix = new int[matrix.length][matrix[0].length];
		int column = 0, row = 0;
		Traversal traversal = Traversal.TopToBottom;


			while (traversal.equals(Traversal.TopToBottom) && row <= matrix.length - 1) {
				if (row == matrix.length - 1) {
					traversal = Traversal.LeftToRight;
					shiftedMatrix[row][column + 1] = matrix[row][column];
					++column;
					
					break;
				} else {
					shiftedMatrix[row+1][column] = matrix[row][column];
					++row;
					
				}
			}

			while (traversal.equals(Traversal.LeftToRight) && column <= matrix[0].length - 1) {
				if (column == matrix[0].length - 1) {
					traversal = Traversal.BottomToTop;
					shiftedMatrix[row - 1][column] = matrix[row][column];
					--row;
					
					break;
				} else {
					shiftedMatrix[row][column + 1] = matrix[row][column];
					++column;
					
				}
			}

			while (traversal.equals(Traversal.BottomToTop) && row >= 0) {
				if (row == 0) {
					traversal = Traversal.RightToLeft;
					shiftedMatrix[row][column - 1] = matrix[row][column];
					--column;
					
					break;
				} else {
					shiftedMatrix[row - 1][column] = matrix[row][column];
					--row;
					
				}
			}

			while (traversal.equals(Traversal.RightToLeft) && column >= 0) {
				if (column == 0) {
					shiftedMatrix[row + 1][column] = matrix[row][column];
					break;
				} else {
					shiftedMatrix[row][column - 1] = matrix[row][column];
					--column;
					
				}
			}

		for(row = 0; row < matrix.length; row++) {
			for(column = 0; column < matrix[0].length; column++) {
				if(row > 0 && row < matrix.length -1 && column > 0 && column < matrix[0].length -1) {
					shiftedMatrix[row][column] = matrix[row][column];
				}
			}
		}
		
		for( row = 0; row < matrix.length; row++) {
			System.out.println(Arrays.toString(shiftedMatrix[row]));
		}
	}
}
