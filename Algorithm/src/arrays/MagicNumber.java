package arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
// Given a sorted array of distinct integers, write a method to find magic indices
public class MagicNumber {
private static final int MINIMUM_SIZE=5; 
public static void main(String[] args) {
	List<Integer> numbers=IntStream.range(0, 19).map((int number)->{
		return number;
	})
	.boxed()
	.collect(Collectors.toList());
	List<Integer> magicNumbers=findMagicNumber(numbers,0,numbers.size()-1);
	System.out.println(magicNumbers);
}

//arr[i]=i is a magic number
public static List<Integer> findMagicNumber(List<Integer> numbers,int lowIndex,int highIndex) {
	if(numbers.get(lowIndex)>highIndex  || numbers.get(highIndex)<lowIndex){
		//magic index does not exist from 
		System.out.println("magic index does not exist from "+lowIndex+" to "+highIndex);
		return Collections.emptyList();
	}
	int partitionSize=highIndex-lowIndex;
	int midIndex=(highIndex+lowIndex)/2;
	System.out.println(lowIndex +" ,"+midIndex+" ,"+ highIndex);
	if(partitionSize > MINIMUM_SIZE) {
		List<Integer> magicNumbersLeft=findMagicNumber(numbers,lowIndex,midIndex-1);
		List<Integer> magicNumbersRight=findMagicNumber(numbers,midIndex,highIndex);
		magicNumbersLeft.addAll(magicNumbersRight);
		return numbers;
	}
	else {
		List<Integer> magicNumbers=new ArrayList<>();
		for(int index=lowIndex;index<= highIndex;index++) {
			if(numbers.get(index) == index) {
				magicNumbers.add(index);
			}
		}
		return magicNumbers;
	}
}

}
