package matrix;

public class BoundaryTraversal {
	enum Traversal{
		LeftToRight,TopToBottom,RightToLeft,BottomToTop
	}
	public static void main(String[] args) {
		int [][]  matrix = {{1 ,   2,   3,   4},
							{5,    6  , 7,   8},
							{9 ,  10 , 11 , 12},
							{13 , 14,  15  ,16}};
		
		int column = 0,row = 0;
		Traversal traversal = Traversal.TopToBottom;


			
		while(traversal.equals(Traversal.TopToBottom) && row <= matrix.length - 1) {
			System.out.println(matrix[row][column]);
			if(row == matrix.length - 1) {
				traversal = Traversal.LeftToRight;
				++column;
				break;
			}
			else {
			++row;
			}
		}
		
		while(traversal.equals(Traversal.LeftToRight) && column <= matrix[0].length - 1) {
			System.out.println(matrix[row][column]);
			if(column == matrix[0].length - 1) {
				traversal = Traversal.BottomToTop;
				--row;
				break;
			}
			else {
			++column;
			}
		}
		
		
		while(traversal.equals(Traversal.BottomToTop) && row >= 0) {
			System.out.println(matrix[row][column]);
			if(row ==  0) {
				traversal = Traversal.RightToLeft;
				--column;
				break;
			}
			else {
			--row;
			}
		}
		
		
		while(traversal.equals(Traversal.RightToLeft) && column >= 0) {
			System.out.println(matrix[row][column]);
			if(column == 0) {
				traversal = Traversal.BottomToTop;
				break;
			}
			else {
			--column;
			}
		}
		

		

		
		
	}

	

}
