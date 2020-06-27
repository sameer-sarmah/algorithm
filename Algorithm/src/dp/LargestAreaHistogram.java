package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LargestAreaHistogram {

	public static void main(String[] args) {
		List<Rectangle> rectangles  = SkylineOfBuilding.populateRectangles();
		int topmostPoint = rectangles.stream().sorted(new RectangleHeightComparator()).findFirst().get().getTop();
		int rightmostPoint = rectangles.stream().sorted(new RectangleRightComparator()).findFirst().get().getRight();		
		int [][] skyline = new int[topmostPoint+1][rightmostPoint+1];
		for(Rectangle rectangle : rectangles) {		
			for(int row = 1;row<= rectangle.getTop() ; row++) {
				for(int column=rectangle.getLeft();column <= rectangle.getRight(); column++) {
					skyline[row][column]=1;
				}
			}
		}
		List<Integer> areaPerRow = new ArrayList<>();
		for(int row = 1;row< skyline.length ; row++) {
			areaPerRow.add(Arrays.stream(skyline[row]).sum());
		}
		System.out.println(areaPerRow.stream().reduce((n1,n2)-> n1+n2).orElseGet( () -> 0));
		
	}
}
