package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KnapSack {
	private int MAX_WEIGHT = 13;
	private List<Item> list = new ArrayList<>();
	private int[][] arr;

	public KnapSack() {
		super();
		populateItems();
	}

	private void populateItems() {
		list.add(new Item(1, 5, 5));
		list.add(new Item(2, 6, 4));
		list.add(new Item(3, 8, 7));
		list.add(new Item(3, 4, 7));
		arr = new int[list.size() + 1][MAX_WEIGHT + 1];
	}

	public void knapSackSelection() {
		for (int i = 1; i <= list.size(); i++) {
			for (int weight = 1; weight <= MAX_WEIGHT; weight++) {
				Item it = list.get(i-1);
				if (weight < it.getWeight()) {
					arr[i][weight] = arr[i - 1][weight];
				} else {
					arr[i][weight] = Math.max(arr[i][weight - it.getWeight()],
							arr[i][weight - it.getWeight()] + it.getValue());
				}

			}
		}
		print();
	}

	private void print() {
		for (int i = 0; i < arr.length; i++) {
			System.out.println(Arrays.toString(arr[i]));
		}
	}
	
	public static void main(String [] args){
		new KnapSack().knapSackSelection();
	}
}

class Item {
	private int id, weight, value;

	public Item(int id, int weight, int value) {
		super();
		this.id = id;
		this.weight = weight;
		this.value = value;
	}

	public int getId() {
		return id;
	}

	public int getWeight() {
		return weight;
	}

	public int getValue() {
		return value;
	}

}