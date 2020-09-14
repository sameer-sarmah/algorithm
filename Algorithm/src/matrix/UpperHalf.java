package matrix;

import java.util.Arrays;

public class UpperHalf {

	public static void main(String[] args) {
		int matrix[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		int[][] upperHalf = new int[matrix.length][matrix.length];
		for (int row = 0; row < matrix.length; row++) {
			for (int column = 0; column < matrix[0].length; column++) {
				if (column >= row) {
					upperHalf[row][column] = matrix[row][column];
				}
			}
		}

		for(int row = 0; row < matrix.length; row++) {
			System.out.println(Arrays.toString(upperHalf[row]));
		}
	}

}
