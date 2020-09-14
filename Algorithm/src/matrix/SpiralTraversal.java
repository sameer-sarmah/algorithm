package matrix;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/*
Input:  1    2   3   4
5    6   7   8
9   10  11  12
13  14  15  16
Output: 1 2 3 4 8 12 16 15 14 13 9 5 6 7 11 10 
*/
public class SpiralTraversal {

	enum Traversal{
		LeftToRight,TopToBottom,RightToLeft,BottomToTop
	}
	
	public static void main(String[] args) {
		int [][]  matrix = {{1 ,   2,   3,   4},
							{5,    6  , 7,   8},
							{9 ,  10 , 11 , 12},
							{13 , 14,  15  ,16}};

		int maxColumns = matrix[0].length;
		int maxRows = matrix.length;
		int row = 0,column = 0;
		int leftColumn=0,rightColumn = matrix[0].length -1;
		int topRow=0,bottomRow = matrix.length -1;
		Traversal traversal = Traversal.LeftToRight;
		
		List<Integer> visitedRows = new ArrayList<>();
		List<Integer> visitedColumns = new ArrayList<>();
		while(visitedRows.size() != maxRows && visitedColumns.size() != maxColumns) {
			while(traversal.equals(Traversal.LeftToRight) && column <= rightColumn) {
				System.out.println(matrix[row][column]);
				if(column == rightColumn) {
					traversal = Traversal.TopToBottom;
					visitedRows.add(row);
					++topRow;
					++row;
					break;
				}
				else {
				++column;
				}
			}
			
			
			while(traversal.equals(Traversal.TopToBottom) && row <= bottomRow) {
				System.out.println(matrix[row][column]);
				if(row == bottomRow) {
					traversal = Traversal.RightToLeft;
					visitedColumns.add(column);
					--column;
					--rightColumn;
					break;
				}
				else {
				++row;
				}
			}
			
			
			while(traversal.equals(Traversal.RightToLeft) && column >= leftColumn) {
				System.out.println(matrix[row][column]);
				if(column == leftColumn) {
					traversal = Traversal.BottomToTop;
					visitedRows.add(row);
					--bottomRow;
					--row;
					break;
				}
				else {
				--column;
				}
			}
			
			
			while(traversal.equals(Traversal.BottomToTop) && row >= topRow) {
				System.out.println(matrix[row][column]);
				if(row == topRow) {
					traversal = Traversal.LeftToRight;
					visitedColumns.add(column);
					++leftColumn;
					++column;
					break;
				}
				else {
				--row;
				}
			}
			
			
		}
	}

}
