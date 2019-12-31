package dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RodCutting {
	private int lengthOfRodToCut = 10;
	private List<MaxRodValue> maxRodValueList = new ArrayList<>();
	private Map<Integer,Integer> length_value=new HashMap<>();
	public RodCutting() {
		super();
		populateRodValues();
	}

	private void populateRodValues() {
		length_value.put(0, 0);
		length_value.put(1, 1);
		length_value.put(2, 5);
		length_value.put(3, 8);
		length_value.put(4, 9);
		length_value.put(5, 10);
		length_value.put(6,17);
		length_value.put(7, 18);
		length_value.put(8,20);
	}
	public void maxRodValue(){
		for(int lengthOfRod=1;lengthOfRod<=lengthOfRodToCut;lengthOfRod++){
			//if maxRodValueList is empty put the value
			if(maxRodValueList.isEmpty()){
				int value=length_value.get(lengthOfRod);
				MaxRodValue max=new MaxRodValue(lengthOfRod,value);
				max.addRodValue(new RodValue(lengthOfRod,value));
				maxRodValueList.add(max);
			}else{
				int max=0;
				int finalizedLengthAtWhichToCut=-1;
				for(int lengthAtWhichToCut=1;lengthAtWhichToCut<=lengthOfRod;lengthAtWhichToCut++){
					Integer firstFragment=length_value.get(lengthAtWhichToCut);
					Integer secondFragment=length_value.get(lengthOfRod-lengthAtWhichToCut);
					if(firstFragment!=null && secondFragment!=null){
						int total=firstFragment + secondFragment;
						if(total>max){
							finalizedLengthAtWhichToCut=lengthAtWhichToCut;
							max=total;
						}	
					}
					
				}

				MaxRodValue maxRV=new MaxRodValue(lengthOfRod,max);
				maxRV.addRodValue(new RodValue(finalizedLengthAtWhichToCut,length_value.get(finalizedLengthAtWhichToCut)));
				if(lengthOfRod-finalizedLengthAtWhichToCut>0){
					maxRV.addRodValue(new RodValue(lengthOfRod-finalizedLengthAtWhichToCut,length_value.get(lengthOfRod-finalizedLengthAtWhichToCut)));	
				}			
				maxRodValueList.add(maxRV);
			}
		}
		printResult();
	}
	
	private void printResult(){
		System.out.println(maxRodValueList);
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