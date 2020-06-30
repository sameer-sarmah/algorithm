package tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/*
 *
 *  length of the fenwick tree is 1 extra than the length of the array it represents
	we dont store anything at index 0the lowest 1 bit tells us how many nodes it is responsible for
	
	responsibleFor in the fenwick tree is the left elements from the fenwickTreeIndex 
	
	 0100 the lowest 1 bit at 4 therefore it is responsible for 1,2,3,4 th elements in the array 
	 1000 the lowest 1 bit at 8 therefore it is responsible for 1-8 th elements in the array 
	 10000 the lowest 1 bit at 16 therefore it is responsible for 1-16 th elements in the array 
	 1010 the lowest 1 bit at 2 therefore it is responsible for 9th,10th element of the array located at index 8 and 9
 	 therefore the number stored at index 10 in fenwick tree is sum of 9th & 10th element in the array

	 
	 remove the right most 1 bit to find the child
	 add the right most 1 bit to find the parent

	 10 (decimal) has binary rep as 1010 ,to find its parent we find the rightmost bit its 10 we add the decimal value i.e 2  therefore 10+2 =12
	 10 (decimal) has binary rep as 1010 ,to find its child we find the rightmost bit its 10 we substract the decimal value i.e 2  therefore 10-2 =8

	 
	 number of bits required to store a number is log n to the base 2 ,e.g to store 16 numbers,0-15, we need 4 bits 2^4 
	 that is the reason the time complexity is O(log n)
	 
	 when we update a node we must also update the nodes which are parents and ancestors for the nodes
	 we find the parent by adding 1 to the lowest 1 bit ,1001 + 1=1010, 1010+ 0010=1100 ,01100+0100=10000
	 parent of 1001 is 1010,parent of 1010 is 1100,parent of 1100 is 10000
	 e.g if we update 1001,1010(parent) ,1100(grandparent), 10000(greatgrandparent)
 * */
public class FenwickTree {
	public static final int MAX=16;
	public static final int MIN=0;
	public static int[] createFenwickTree(int [] numbers) {

		int [] fenwickTree=new int[numbers.length+1];
		for(int index=0;index<numbers.length;index++) {
			int fenwickTreeIndex=index+1;
			int responsibleFor=Integer.lowestOneBit(fenwickTreeIndex);
			int sum=0;
			for(int indexInArray=fenwickTreeIndex-responsibleFor;indexInArray<fenwickTreeIndex;indexInArray++) {
				sum+=numbers[indexInArray];
			}
			fenwickTree[fenwickTreeIndex]=sum;
			
		}
		return fenwickTree;
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
	// find children and all descendants of the index
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
	// find parent and all ancestors of the index
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

	//10 i.e 1010 will give 2 , lowest one bit is  10
	private static int findParent(int index) {
		String binary=Integer.toBinaryString(Integer.lowestOneBit(index));
		return index+Integer.parseInt(binary,2);
	}
	
	//10 i.e 1010 will give 1000
	private static int findChild(int index) {
		String binary=Integer.toBinaryString(Integer.lowestOneBit(index));
		return index - Integer.parseInt(binary,2);
	}

}
