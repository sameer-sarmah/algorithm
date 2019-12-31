package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KnapSack {
	private int MAX_WEIGHT = 13;
	private List<Item> availableItems = new ArrayList<>();
	private int[][] itemsToWeightMatrix;

	public KnapSack() {
		super();
		populateItems();
	}

	private void populateItems() {
		//assume these are the available items in infinite numbers
		availableItems.add(new Item(1, 5, 5));
		availableItems.add(new Item(2, 6, 4));
		availableItems.add(new Item(3, 8, 7));
		availableItems.add(new Item(4, 4, 7));
		//the items are the rows and start from index 1
		//weight are the columns and start from index 1
		itemsToWeightMatrix = new int[availableItems.size() + 1][MAX_WEIGHT + 1];
	}

	public void knapSackSelection() {
		for (int i = 1; i <= availableItems.size(); i++) {
			Item it = availableItems.get(i-1);
			for (int weight = 1; weight <= MAX_WEIGHT; weight++) {
				
				//item too heavy to fulfill the weight criterion
				if (weight < it.getWeight()) {
					itemsToWeightMatrix[i][weight] = itemsToWeightMatrix[i - 1][weight];
				} 
				else {
					int existingValueWithoutThisItem = itemsToWeightMatrix[i][weight - it.getWeight()];
					int updatedValueAfterIncludingThisItem = itemsToWeightMatrix[i][weight - it.getWeight()] + it.getValue();
					itemsToWeightMatrix[i][weight] = Math.max(existingValueWithoutThisItem,updatedValueAfterIncludingThisItem);
				}

			}
		}
		print();
	}

	private void print() {
		for (int i = 0; i < itemsToWeightMatrix.length; i++) {
			System.out.println(Arrays.toString(itemsToWeightMatrix[i]));
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