package matrix;

import java.util.Arrays;

/*
Input : {{ 1,  2,  3},
    	{ 4,  5,  6},
    	{ 7,  8,  9}}
	 
	 	 
90 degree
		{{ 3,  6,  9}, 
		{ 2,  5,  8}, 
		{ 1,  4,  7}}


180 degree    
		{{ 9, 8, 7},
		 { 6, 5, 4},
		 { 3, 2, 1}} 
*/

public class OneEightyDegreeShift {

	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		int[][] rotatedMatrix = new int[matrix.length][matrix[0].length];
		int startColumn = matrix[0].length - 1;
		int startRow = matrix.length - 1;
		for (int row = startRow; row >= 0; row--) {
			for (int column = startColumn; column >= 0; column--) {
				int rotatedMatrixColumn = matrix.length - column - 1;
				int rotatedMatrixRow = matrix.length - row - 1;
				rotatedMatrix[rotatedMatrixRow][rotatedMatrixColumn] = matrix[row][column];
			}
		}

		for(int row = 0; row < matrix.length; row++) {
			System.out.println(Arrays.toString(rotatedMatrix[row]));
		}

	}

}
