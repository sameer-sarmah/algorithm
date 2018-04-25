package dp;

public class ContinousSortedSubArray {
	public static void main(String[] args) {

		int[] numbers = {20,12,4,5,6,7,1,2,3,4,5};
		int[] storedLength=new  int[numbers.length];
		int endingIndex = 0;
		int maxLength=0;
		for(int index=0;index<numbers.length;index++) {
			if(index==0) {
				storedLength[index]=1;
			}
			else {
				if(numbers[index]>numbers[index-1]) {
					storedLength[index]=storedLength[index-1]+1;
				}
				else {
					storedLength[index]=1;
				}
			}
			if(storedLength[index]>maxLength) {
				maxLength=storedLength[index];
				endingIndex=index;
			}
		}
		System.out.println("max length of continuos sorted sub array is "+maxLength+" starting from "+(endingIndex-maxLength+1)+" till index "+endingIndex);
	}
}
