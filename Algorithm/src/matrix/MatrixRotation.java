package matrix;

import java.util.Arrays;

/*
 arr[0][1] placed at arr[arr.length-1][0]
 arr[1][1] placed at arr[arr.length-1][1]
 arr[2][1] placed at arr[arr.length-1][2]
 
 arr[0][1] placed at arr[arr.length-2][0]
 arr[1][1] placed at arr[arr.length-2][1]
 arr[2][1] placed at arr[arr.length-2][2]
 
 arr[0][2] placed at arr[arr.length-3][0]
 arr[1][2] placed at arr[arr.length-3][1]
 arr[2][2] placed at arr[arr.length-3][2]
 
 a prime number greater than the highest number let's say= 11
 
 original number obtain by %
 new number obtain by /

 * */
public class MatrixRotation {
	private static final int PRIME_NUMBER = 11;

	public static void main(String[] args) {
		int[][] arr = { { 1, 2, 3 }, 
						{ 4, 5, 6 }, 
						{ 7, 8, 9 } };
		
		//encode all the cells
		 for(int col=0;col < arr.length ; col++){
			  for(int row=0;row < arr.length ; row++){
			       int newNumber=arr[row][col]%PRIME_NUMBER;
				   int original=arr[arr.length-1-col][row]%PRIME_NUMBER;
			       int encoded = newNumber*PRIME_NUMBER+original;
				   arr[arr.length-1-col][row]=encoded;
			  }
			 }
		 
			//decode all the cells
		 for(int col=0;col < arr.length ; col++){
			  for(int row=0;row < arr.length ; row++){    
				   arr[arr.length-1-col][row]=arr[arr.length-1-col][row]/PRIME_NUMBER;
			  }
			 }
		 
		 //print
		 for(int row=0;row < arr.length ; row++) {
			System.out.println(Arrays.toString(arr[row])); 
		 }

	}
}
