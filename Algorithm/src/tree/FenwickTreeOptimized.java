package algorithm.tree;

import java.util.Arrays;
import java.util.stream.IntStream;

public class FenwickTreeOptimized {

	public static final int MAX=16;
	public static final int MIN=0;
	
	public static void main(String[] args) {
		int [] numbers=IntStream.rangeClosed(1, 16).toArray();
		int[] fenwickTree=createFenwickTree(numbers);
		System.out.println(Arrays.toString(fenwickTree));
		System.out.println(sum(7,fenwickTree));
		update(1,3,fenwickTree);
		System.out.println(Arrays.toString(fenwickTree));
	}
	
	
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
	
	public static int sum(int index,int[] fenwickTree) {
		int sum=0;
		while(index>0) {
			sum+=fenwickTree[index];
			int responsibleFor=Integer.lowestOneBit(index);
			index-=responsibleFor;
		}
		return sum;
	}
	
	public static int sumRange(int fromIndex,int toIndex,int[] fenwickTree) {
	    return sum(toIndex,fenwickTree)-sum(fromIndex,fenwickTree);
	}
	
	public static void update(int index,int value,int[] fenwickTree) {
		while(index<=MAX) {
			fenwickTree[index]+=value;
			index+=Integer.lowestOneBit(index);
		}
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
