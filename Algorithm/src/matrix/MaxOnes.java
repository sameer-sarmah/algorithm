package matrix;

public class MaxOnes {

	public static void main(String[] args) {
		int matrix[][] = { { 0, 0, 0, 1 }, { 0, 1, 1, 1 }, { 1, 1, 1, 1 }, { 0, 0, 0, 0 } };

		int rowWithMaxOne = -1;
		int maxOnes = 0;

		for (int row = 0; row < matrix.length; row++) {
			for (int column = 0; column < matrix[0].length; column++) {

				if (matrix[0].length - column <= maxOnes) {
					break;
				}

				if (matrix[row][column] == 1) {
					int numberOfOnes = matrix[0].length - column;
					if (numberOfOnes > maxOnes) {
						rowWithMaxOne = row;
						maxOnes = numberOfOnes;
						break;
					}
				}
			}
		}
		
		System.out.println(String.format("the row with max 1s is %d with %d 1s", rowWithMaxOne,maxOnes));

	}

}
