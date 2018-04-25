package algorithm.dp;

import java.util.Arrays;

public class EditDistance {
	private final String from = "kitten", to = "knitting";
	private int[][] arr = new int[from.length() + 1][to.length() + 1];

	public void calculateEditDistance() {
		for (int i = 1; i <= from.length(); i++) {
			for (int j = 1; j <= to.length(); j++) {
				if (i == j && from.charAt(i-1) == to.charAt(j-1)) {
					// copy
					arr[i][j] = arr[i - 1][j - 1];
				} else if (i != j && from.charAt(i-1) == to.charAt(j-1)) {
					arr[i][j] = arr[i][j - 1];
				} else {
					if(i>1)
					arr[i][j] =Math.min(arr[i-1][j], arr[i][j - 1] + 1);
					else
						arr[i][j] =arr[i][j - 1] + 1;
				}
			}
		}
		print();
	}

	private void print() {
		for (int[] row : arr) {
			System.out.println(Arrays.toString(row));
		}
	}

	public static void main(String[] args) {
		new EditDistance().calculateEditDistance();
	}
}
