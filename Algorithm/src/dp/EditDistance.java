package dp;

import java.util.Arrays;
import java.util.Collections;

public class EditDistance {
	private static final String from = "kitten", to = "knitting";
	private static int[][] arr = new int[from.length() + 1][to.length() + 1];

	public void calculateEditDistance() {
		//when the source and target both are empty string ,i.e transform "" to ""
		arr[0][0]=0;
		
		//when the source is an empty string ,i.e transform "" to "knitting",insert every character
		for(int targetIndex = 1 ; targetIndex < to.length() ; targetIndex++) {
			arr[0][targetIndex] = targetIndex;
		}
		
		//when the target is an empty string ,i.e transform "kitten" to "",delete every character
		for(int sourceIndex = 1 ; sourceIndex < from .length() ; sourceIndex++) {
			arr[sourceIndex][0] = sourceIndex;
		}
		/*
		 * insertion is a shift to the right,arr[source][target+1]
		 * deletion is a shift to the bottom,arr[source+1][target]
		 * copy/substitution is a shift diagonally,arr[source+1][target+1]
		 * 
		 * cost of insertion,deletion,substitution =1
		 * cost of copying = 0
		 * */
		for (int sourceIndex = 1; sourceIndex <= from.length(); sourceIndex++) {
			for (int targetIndex = 1; targetIndex <= to.length(); targetIndex++) {
				if (sourceIndex == targetIndex && from.charAt(sourceIndex-1) == to.charAt(targetIndex-1)) {
					// copy
					arr[sourceIndex][targetIndex] = arr[sourceIndex - 1][targetIndex - 1];
				} 
				else {
					int insert = arr[sourceIndex][targetIndex -1 ] + 1;
					int delete = arr[sourceIndex - 1][targetIndex] + 1;
					int substitute = arr[sourceIndex - 1][targetIndex -1 ] + 1;
					arr[sourceIndex][targetIndex] = Math.min(Math.min(insert, delete),substitute);
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
