package dp;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SkylineOfBuilding {

	
	public static void main(String[] args) {
		List<Rectangle> rectangles  = populateRectangles();
		calculateSkyLine(rectangles);
	}
	
	private static void calculateSkyLine(List<Rectangle> rectangles ) {
		List<Point> skylinePoints = new ArrayList<Point>();
		int topmostPoint = rectangles.stream().sorted(new RectangleHeightComparator()).findFirst().get().getTop();
		int rightmostPoint = rectangles.stream().sorted(new RectangleRightComparator()).findFirst().get().getRight();		
		int [][] skyline = new int[topmostPoint+1][rightmostPoint+1];
		for(Rectangle rectangle : rectangles) {		
			for(int row = 1;row<= rectangle.getTop() ; row++) {
				for(int column=rectangle.getLeft();column <= rectangle.getRight(); column++) {
					skyline[row][column]=skyline[row][column]+1;
				}
			}
		}
		

		int[] height = calculateHeightForColumns(skyline);
		boolean[] hasHeightChanged = columnsWithHeightChanges(height);
		
		for (int column = 1; column <= rightmostPoint; column++) {
			for (int row = 1; row < skyline.length; row++) {
				if (row == 1 && skyline[row][column] == 1) {
					skylinePoints.add(new Point(column,row ));
					System.out.println("added (" + column  + "," + row + ")");
				} else {
					if (hasHeightChanged[column] == true) {

						skylinePoints.add(new Point(column,height[column]));
						System.out.println("added (" + column + "," + height[column] + ")");
						break;
					}
				}

			}
		}
		System.out.println(skylinePoints);
	}
	
	private static int[] calculateHeightForColumns(int [][] skyline ) {
		int[] height = new int[skyline[0].length];
		for(int column =1;column < skyline[0].length ; column++) {
			int columnHeight=0;
			for(int row = 1;row< skyline.length;row++ ) {
				if(skyline[row][column] > 0) {
					++columnHeight;
				}
			}
			height[column] = columnHeight;
		}
		return height;
	}
	
	private static boolean[] columnsWithHeightChanges(int[] height ) {
		boolean[] hasHeightChanged = new boolean[height.length];
		for(int column =1;column < height.length ; column++) {
			boolean isFirstElement = column == 1;
			boolean isLastElement = column == height.length-1;
			boolean ifEqualToLeftElement = false;
			boolean ifEqualToRightElement = false;
			
			if(!isFirstElement) {
				ifEqualToLeftElement = height[column-1] != height[column];
			}
			
			if(!isLastElement) {
				ifEqualToRightElement = height[column+1] != height[column];
			}
			
			if(!(ifEqualToLeftElement && ifEqualToRightElement)) {
				hasHeightChanged[column]=true;
			}
			else {
				hasHeightChanged[column]=false;
			}
		}
		return hasHeightChanged;
	} 
	
	public static List<Rectangle> populateRectangles() {
		List<Rectangle> rectangles  = new ArrayList<>();
		rectangles.add(new Rectangle(1, 3, 3));
		rectangles.add(new Rectangle(2,4,4));
		rectangles.add(new Rectangle(5,8,2));
		rectangles.add(new Rectangle(6,7,4));
		rectangles.add(new Rectangle(8,9,3));
		return rectangles;
	}
	

}
class Rectangle{
	private int left,right,top;
	
	public Rectangle(int left, int right, int top) {
		super();
		this.left = left;
		this.right = right;
		this.top = top;
	}

	public int getLeft() {
		return left;
	}

	public int getRight() {
		return right;
	}

	public int getTop() {
		return top;
	}
	
}

class RectangleHeightComparator implements Comparator<Rectangle>{

	@Override
	public int compare(Rectangle rect1, Rectangle rect2) {
		if(rect1.getTop() < rect2.getTop()) {
			return 1;
		}
		else if(rect1.getTop() > rect2.getTop()) {
			return -1;
		}
		return 0;
	}
	
}

class RectangleRightComparator implements Comparator<Rectangle>{

	@Override
	public int compare(Rectangle rect1, Rectangle rect2) {
		if(rect1.getRight() < rect2.getRight()) {
			return 1;
		}
		else if(rect1.getRight() > rect2.getRight()) {
			return -1;
		}
		return 0;
	}
	
}

