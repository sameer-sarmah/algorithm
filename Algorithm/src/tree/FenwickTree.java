package tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/*
 *     the lowest 1 bit tells us how many nodes it is responsible for
 
 e.g 1010 the lowest 1 bit at 2 therefore it is responsible for 9,10
     0100 the lowest 1 bit at 4 therefore it is responsible for 1,2,3,4
	 1000 the lowest 1 bit at 8 therefore it is responsible for 1-8
	 10000 the lowest 1 bit at 16 therefore it is responsible for 1-16
	 
	 how to calculate prefix sum
	 e.g example 1011 =1011 + 1010 + 1000 ,remove the lowest bit each time
	 
	 number of bits required to store a number is log n to the base 2 ,e.g to store 16 numbers,0-15, we need 4 bits 2^4 
	 that is the reason the time complexity is O(log n)
	 
	 when we update a node we must also update the nodes which are responsible for the nodes
	  e.g if we update 1001  ,we have to update 1001 ,1010 ,1100, 10000
	  we find the parent by adding 1 to the lowest 1 bit ,1001 + 1=1010, 1010+ 0010=1100 ,01100+0100=10000
	  
	  to find lowest 1 bit Integer.lowestOnebit 
 * */
public class FenwickTree {
	public static final int MAX=16;
	public static final int MIN=0;
	public static int[] createFenwickTree(int [] numbers) {
		int [] tree=new int[numbers.length+1];
		for(int index=0;index<numbers.length;index++) {
			int fenwickTreeIndex=index+1;
			int responsibleFor=Integer.lowestOneBit(fenwickTreeIndex);
			int sum=0;
			for(int i=fenwickTreeIndex-responsibleFor;i<fenwickTreeIndex;i++) {
				sum+=numbers[i];
			}
			tree[fenwickTreeIndex]=sum;
			
		}
		return tree;
	}
	

	
	public static void main(String[] args) {
		int [] numbers=IntStream.rangeClosed(1, 16).toArray();
		int[] fenwickTree=createFenwickTree(numbers);
		System.out.println(Arrays.toString(fenwickTree));
		System.out.println(sum(7,fenwickTree));
		update(1,3,fenwickTree);
		System.out.println(Arrays.toString(fenwickTree));
	}
	
	public static int sum(int toIndex,int[] fenwickTree) {
		List<Integer> indices=getListOfIndicesForPrefixSum( toIndex);
		int sum=0;
		for(int index :indices) {
			sum+=fenwickTree[index];
		}
		return sum;
	}
	
	public static int sumRange(int fromIndex,int toIndex,int[] fenwickTree) {
		return sum(toIndex,fenwickTree)- sum(fromIndex,fenwickTree);
	}
	
	public static void update(int index,int value,int[] fenwickTree) {
		List<Integer> indices=getListOfIndicesForUpdate(index);
		for(int i :indices) {
			fenwickTree[i]=fenwickTree[i]+value;
		}
	}
	
	//9 will give [9, 8]
	public static List<Integer> getListOfIndicesForPrefixSum(int index){
		List<Integer> indices=new ArrayList<>();
		indices.add(index);
		int prefixIndex=index;
		while(prefixIndex>MIN) {
			prefixIndex=findChild(prefixIndex);
			if(prefixIndex>0)
			indices.add(prefixIndex);
		}
		return indices;
	}
	// 9 will give [9, 10, 12, 16]
	public static List<Integer> getListOfIndicesForUpdate(int index){
		List<Integer> indices=new ArrayList<>();
		indices.add(index);
		int parentIndex=index;
		while(parentIndex<MAX) {
			parentIndex=findParent(parentIndex);
			indices.add(parentIndex);
		}
		return indices;
	}
	//10 i.e 1010 will give 1000
	private static int removeLowestOneBit(int number) {
		String binary=Integer.toBinaryString(Integer.lowestOneBit(number));
		int lowestOneBitIndex=binary.length()-binary.indexOf("1");
	    binary=Integer.toBinaryString(number);
		int subStringIndex=binary.length()-lowestOneBitIndex;
		String updatedStr=binary.substring(0, subStringIndex)+"0"+binary.substring(subStringIndex+1);
		return Integer.parseInt(updatedStr,2); 
	}
	
	//10 i.e 1010 will give 2 , lowest one bit is  10
	private static int getLowestOneBit(int number) {
		String binary=Integer.toBinaryString(Integer.lowestOneBit(number));
		int lowestOneBitIndex=binary.length()-binary.indexOf("1");
        StringBuilder str=new StringBuilder();
        str.append("1");
        int counter=lowestOneBitIndex-1;
        while(counter>0) {
        	str.append("0");
        	counter--;
        }
		return Integer.parseInt(str.toString(),2); 
	}
	
	private static int findParent(int number) {
		int numberToAdd=getLowestOneBit(number);
		return number+numberToAdd;
	}
	
	private static int findChild(int number) {
		return removeLowestOneBit(number);
	}
	//method producing same result as Integer.lowestOneBit
	@SuppressWarnings("unused")
	private static int lowestOneBit(int number) {
		String binaryRep= Integer.toBinaryString(number);
		String twosComplement= Integer.toBinaryString((~number)+1);
		twosComplement=twosComplement.substring(twosComplement.length()-binaryRep.length(),twosComplement.length());
		String str= Integer.toBinaryString(Integer.parseInt(binaryRep, 2) & Integer.parseInt(twosComplement, 2));
		return str.length()-str.indexOf('1');
	}
}
