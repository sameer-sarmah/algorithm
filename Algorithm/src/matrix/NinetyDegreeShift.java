package matrix;

import java.util.Arrays;
/*

Input : {{ 1,  2,  3},
         { 4,  5,  6},
         { 7,  8,  9}}
Output : {{ 9, 8, 7},
		  { 6, 5, 4},
		  { 3, 2, 1}} 

column = 2
rotatedMatrixRow = 0

column = 1
rotatedMatrixRow = 1

column= 0
rotatedMatrixRow = 2

row = 0
rotatedMatrixColumn =0

row = 1
rotatedMatrixColumn = 1

row = 1
rotatedMatrixColumn = 1
*/
public class NinetyDegreeShift {

	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		int[][] rotatedMatrix = new int[matrix.length][matrix[0].length];
		int startColumn = matrix[0].length - 1;

		for (int column = startColumn; column >= 0; column--) {
			for (int row = 0; row < matrix.length; row++) {
				int rotatedMatrixColumn = row;
				int rotatedMatrixRow = matrix.length - column - 1;
				rotatedMatrix[rotatedMatrixRow][rotatedMatrixColumn] = matrix[row][column];
			}
		}

		for(int row = 0; row < matrix.length; row++) {
			System.out.println(Arrays.toString(rotatedMatrix[row]));
		}
	}

}
