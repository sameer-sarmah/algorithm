package dp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KTransactions {

	private static Map<Integer,Integer> dayNumberToPrice = new HashMap<>();
	private static int NUMBER_OF_TRANSACTIONS = 3;
	private static int[][] profit = new int[NUMBER_OF_TRANSACTIONS+ 1][7];
	static {
		dayNumberToPrice.put(1, 10);
		dayNumberToPrice.put(2, 22);
		dayNumberToPrice.put(3, 5);
		dayNumberToPrice.put(4, 75);
		dayNumberToPrice.put(5, 65);
		dayNumberToPrice.put(6, 80);
	}
	
	public static void main(String[] args) {
		for (int transactionNo = 1 ; transactionNo <= NUMBER_OF_TRANSACTIONS ; transactionNo++) {
			for(int day = 1;day <= dayNumberToPrice.size();day++) {
				int profitInPreviousDay = profit[transactionNo][day -1];
				List<Integer> profits = new ArrayList<>();
				profits.add(profitInPreviousDay);
				/*
				 * The below for loop finds all the profit possible by shifting the date of purchase,the date of sell is = day
				 * Thus the profit only for this transaction is  dayNumberToPrice.get(day) - dayNumberToPrice.get(dayWhenPreviousTransactionWasCompleted)
				 * We also have to add the profit made in the previous transaction by selling on  profit[transactionNo - 1][dayWhenPreviousTransactionWasCompleted]
				 * Lets take a sample example,if we want to calculate profit on day = 4 for transaction = 2
				 * 
				 * calculate when stock was purchased on day = 1 and sold on day = 4,with no previous transaction
				 * calculate when stock was purchased on day = 2 and sold on day = 4,with previous transaction stored in profit[1][2]
				 * calculate when stock was purchased on day = 3 and sold on day = 4,with previous transaction stored in profit[1][3]
				 * 
				 * 
				 * */
				for(int dayWhenPreviousTransactionWasCompleted=1; dayWhenPreviousTransactionWasCompleted < day ;dayWhenPreviousTransactionWasCompleted++) {
					int profitIfBoughtOnThisDay = dayNumberToPrice.get(day) - dayNumberToPrice.get(dayWhenPreviousTransactionWasCompleted);
					profits.add(profitIfBoughtOnThisDay + profit[transactionNo - 1][dayWhenPreviousTransactionWasCompleted]);//using memoized data
				}
				profit[transactionNo][day] = Collections.max(profits);
				System.out.println("Profit for transaction "+transactionNo+" on Day: "+day+" is: "+ profit[transactionNo][day]);
			}
		}
	}
	
}
