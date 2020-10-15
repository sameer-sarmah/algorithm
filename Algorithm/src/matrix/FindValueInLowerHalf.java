package matrix;
/* 
Your previous Plain Text content is preserved below:

If we have a triangular matrix of the form below. Use recursion to find the number at a given position (i,j). i represents the row index j represents column index.


1  
1 1 
1 2 1 
1 3 3 1 
1 4 6 4 1 
1 5 10 10 5 1 
1 6 15 20 15 6 1 

i,j (6,2) = 15
*/
public class FindValueInLowerHalf {

	public static void main(String[] args) {
	    int N = 10;
	    int row = 6;
	    int col = 2;
	    int[][] cache = new int[N][N]; 
	    for(int rowIndex = 0;rowIndex< N;rowIndex++){
	      for(int colIndex = 0;colIndex< N;colIndex++){
	          cache[rowIndex][colIndex] = -1;
	      }
	    }
	    System.out.println(findValue(cache,row,col));
	    
	    // for(int rowIndex = 0;rowIndex< N;rowIndex++){
	    //  System.out.println(Arrays.toString(cache[rowIndex]));
	    // }
	  }
	  
	  private static int findValue(int[][] cache,int row,int col ){
	    if(row == 0 && col == 0)
	      return 1;  
	    else if(row < col)
	      return 0;
	    else if(row < 0 || col < 0)
	      return 0;
	    else{
	     if(cache[row][col] > 0){
	        System.out.println("returning from cache "+row +" ,"+col);
	        return cache[row][col];
	     }
	     else{
	       int value =  findValue(cache,row - 1,col) + findValue(cache,row - 1,col -1);
	       cache[row][col] = value;
	       System.out.println("evaluated value for "+row +" ,"+col +" is "+value);
	       return value;
	     } 
	    }
	    
	  }

}
