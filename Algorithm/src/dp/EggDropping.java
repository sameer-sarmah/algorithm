package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EggDropping {
	private int numOfEggs = 3;
	private int numOfFloors = 5;
	/*eggs are represented by rows & starts at index 1
	number of floors are represented by columns & starts at index 1
	the value in the matrix stores the maximum number of attempts
	2 floors and 1 egg ,if the egg breaks in 1st floor ,first param is 1 as the no of attempts is 1
	if the egg does not break we have 1 egg and 1 more floor to test in addition to this attempt i.e total is 2
	we will take the maximum of the two therefore 2
	 * */
	private int[][] eggToFloorMatrix = new int[numOfEggs + 1][numOfFloors + 1];

	public void populateData() {
		List<Integer> numberOfAttempts = new ArrayList<>();
		for (int egg = 1; egg <= numOfEggs; egg++) {
			for (int floor = 1; floor <= numOfFloors; floor++) {
				if(egg==1){//for egg==1 has to be sequential since we can't be aggressive as we have no spare egg
					eggToFloorMatrix[egg][floor]=floor;//e.g for egg=1,floor=3 we can't start from floor=2 since it is not certain weather the egg will survive a drop from 1st floor
				}else{
				numberOfAttempts.clear();
				for (int floorFromWhichToDropTheEgg = 1; floorFromWhichToDropTheEgg <= floor; floorFromWhichToDropTheEgg++) {
					numberOfAttempts.add(Math.max(floorFromWhichToDropTheEgg, eggToFloorMatrix[egg][floor - floorFromWhichToDropTheEgg]+1));//first parameter means where the egg breaks,second is when the egg doesnot break
				}/*e.g 2 floors and 1 egg ,if the egg breaks in 1st floor ,first param is 1 as the no of attempts is 1
				if the egg does not break we have 1 egg and 1 more floor to test in addition to this attempt i.e total is 2
				*/
			eggToFloorMatrix[egg][floor]=Collections.min(numberOfAttempts);

			}
			}
		}
		print();
	}
	
	private void print(){
		for(int [] row : eggToFloorMatrix){
			System.out.println(Arrays.toString(row));
		}
	}
	
	public static void main(String[] args){
		new EggDropping().populateData();
	}

}
