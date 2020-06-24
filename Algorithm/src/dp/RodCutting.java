package dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RodCutting {
	private int lengthOfRodToCut = 10;
	private Map<Integer,MaxRodValue> length_maxRodValue=new HashMap<>();
	private Map<Integer,Integer> length_value=new HashMap<>();
	public RodCutting() {
		super();
		populateRodValues();
	}

	private void populateRodValues() {
		length_value.put(0, 0);
		length_value.put(1, 1);
		length_value.put(2, 5);
		//length_value.put(3, 8);
		//length_value.put(4, 9);
		length_value.put(5, 10);
		//length_value.put(6,17);
		//length_value.put(7, 18);
		length_value.put(8,20);
	}
	public void maxRodValue(){
			
			for(int lengthOfRod=1;lengthOfRod<=lengthOfRodToCut;lengthOfRod++){
				
					int max=0;
					int finalizedLengthAtWhichToCut=-1;
					for(int lengthAtWhichToCut=1;lengthAtWhichToCut<=lengthOfRod;lengthAtWhichToCut++){
						Integer firstFragment = null;
						Integer secondFragment = null;
						if(length_maxRodValue.get(lengthAtWhichToCut) != null) {
							firstFragment = length_maxRodValue.get(lengthAtWhichToCut).getValue();
						}
						else {
							firstFragment=length_value.get(lengthAtWhichToCut);
						}
						
						if(length_maxRodValue.get(lengthOfRod-lengthAtWhichToCut) != null) {
							secondFragment = length_maxRodValue.get(lengthOfRod-lengthAtWhichToCut).getValue();
						}
						else {
							secondFragment=length_value.get(lengthOfRod-lengthAtWhichToCut);
						}
						
						if(firstFragment!=null && secondFragment!=null){
							int total=firstFragment + secondFragment;
							if(total>max){
								finalizedLengthAtWhichToCut=lengthAtWhichToCut;
								max=total;
							}	
						}
						
					}
					Integer firstFragmentValue = null;
					Integer secondFragmentValue = null;
					MaxRodValue maxRV=new MaxRodValue(lengthOfRod,max);
					if(length_maxRodValue.get(finalizedLengthAtWhichToCut) != null) {
						firstFragmentValue = length_maxRodValue.get(finalizedLengthAtWhichToCut).getValue();
					}
					else {
						firstFragmentValue =length_value.get(finalizedLengthAtWhichToCut);
					}
					
					if(length_maxRodValue.get(lengthOfRod-finalizedLengthAtWhichToCut) != null) {
						secondFragmentValue = length_maxRodValue.get(lengthOfRod-finalizedLengthAtWhichToCut).getValue();
					}
					else {
						secondFragmentValue =length_value.get(lengthOfRod-finalizedLengthAtWhichToCut);
					}
					
					maxRV.addRodValue(new RodValue(finalizedLengthAtWhichToCut,firstFragmentValue));
					if(secondFragmentValue != null && secondFragmentValue > 0){
						maxRV.addRodValue(new RodValue(lengthOfRod-finalizedLengthAtWhichToCut,secondFragmentValue));	
					}
					length_maxRodValue.put(lengthOfRod, maxRV);
				}
			
		
		printResult();
	}
	
	private void printResult(){
		System.out.println(length_maxRodValue);
	}
	
	public static void main(String [] args){
		new RodCutting().maxRodValue();
	}
}

class RodValue {
	private int length, value;

	public RodValue(int length, int value) {
		super();
		this.length = length;
		this.value = value;
	}

	public int getLength() {
		return length;
	}

	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "RodValue [length=" + length + ", value=" + value + "]";
	}
	
	

}
class MaxRodValue extends RodValue{
    private List<RodValue> rodFragments = new ArrayList<>();
	public MaxRodValue(int length, int value) {
		super(length, value);
	}
	public List<RodValue> getListRodValue() {
		return rodFragments;
	}
	public void addRodValue(RodValue rodValue) {
		this.rodFragments.add(rodValue);
	}
	
	
	
	
}