package algorithm.dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RodCutting {
	private int N = 10;
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
		for(int l=1;l<=N;l++){
			//if maxRodValueList is empty put the value
			if(maxRodValueList.isEmpty()){
				int value=length_value.get(l);
				MaxRodValue max=new MaxRodValue(l,value);
				max.addRodValue(new RodValue(l,value));
				maxRodValueList.add(max);
			}else{
				int max=0;
				int index=-1;
				for(int i=1;i<=l;i++){
					Integer f=length_value.get(i);
					Integer s=length_value.get(l-i);
					if(f!=null && s!=null){
						int total=f+s;
						if(total>max){
							index=i;
							max=total;
						}	
					}
					
				}

				MaxRodValue maxRV=new MaxRodValue(l,max);
				maxRV.addRodValue(new RodValue(index,length_value.get(index)));
				if(l-index>0){
					maxRV.addRodValue(new RodValue(l-index,length_value.get(l-index)));	
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
    private List<RodValue> listRodValue = new ArrayList<>();
	public MaxRodValue(int length, int value) {
		super(length, value);
	}
	public List<RodValue> getListRodValue() {
		return listRodValue;
	}
	public void addRodValue(RodValue rodValue) {
		this.listRodValue.add(rodValue);
	}
	
	
	
	
}