package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
 Input: 
S = {a, b, c, d}
Output:
{}, {a} , {b}, {c}, {d}, {a,b}, {a,c},
{a,d}, {b,c}, {b,d}, {c,d}, {a,b,c}, 
{a,b,d}, {a,c,d}, {b,c,d}, {a,b,c,d}
 * */
public class AllSubsetOfSet {
  
	public static void main(String[] args) {
		 List<String> set= Arrays.asList("d","c","b","a");
		 List<String> subSet= subSetsOfASet(set);
		 Collections.sort(subSet);
		 System.out.println(subSet);
	}
	
	public static List<String> subSetsOfASet(List<String> set){
		int numberOfSubSets= (int) Math.pow(2, set.size());
		List<String> subSet = IntStream
		.range(0, numberOfSubSets)
		.mapToObj((int number)->{
			return stringCorrespondingToBinary(number,set);
		})
		.collect(Collectors.toList());
		return subSet;
		
	}
	
    public static String stringCorrespondingToBinary(int number,List<String> set){
		String binary=Integer.toBinaryString(number);
		int lengthOfBinaryString = set.size();
		int zerosToPrepend=lengthOfBinaryString-binary.length();
		StringBuilder zerosString=new StringBuilder("");
		IntStream.rangeClosed(1, zerosToPrepend).forEach((int n)->{ zerosString.append("0");});
		binary=zerosString.toString()+binary;
		StringBuilder string=new StringBuilder("");
		char[] characs=binary.toCharArray();
		for(int index=0;index<characs.length;index++) {
			if(characs[index]=='1') {
				string.append(set.get(index));
			}
		}
		System.out.println(binary +"    "+string.toString());
		return string.toString();
	}

}
