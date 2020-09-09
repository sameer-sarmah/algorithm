package arrays;

public class SmallestSubArraySumToAGivenNumber {

	public static void main(String[] args) {
		int numbers[] = {1, 4, 45, 6, 0, 19};
		int required  =  51;
	    usingTwoLoops(numbers, required); 
	}
	
	private static void usingTwoLoops(int numbers[],int required) {
		int subArrayStartIndex = 0;
		int subArrayEndIndex = 0;
		int subArrayLength = numbers.length;

		for(int startIndex = 0;startIndex< numbers.length ; startIndex++){
			int localSum = 0;
			for(int subArrayIndex = startIndex;subArrayIndex<numbers.length;subArrayIndex++){
				localSum += numbers[subArrayIndex];
				if(localSum >= required && (subArrayIndex - startIndex) < subArrayLength ){
				 subArrayStartIndex =startIndex;
				 subArrayEndIndex= subArrayIndex;
				 subArrayLength = subArrayIndex - startIndex;
				 System.out.println(String.format("local subarray start index :%d  and end index :%d", subArrayStartIndex,subArrayEndIndex));
				 break;
				}
			}
		} 
		 System.out.println(String.format("subarray start index :%d  and end index :%d", subArrayStartIndex,subArrayEndIndex));
	}

}
