package dp;

import java.util.ArrayList;
import java.util.List;

public class CoinChange {
	int coins[] = { 1, 2, 3, 4 };
	int maxSum = 5;
	int numCoins[][] = new int[coins.length + 1][maxSum + 1];
	List<Integer>[][] usedCoins = new ArrayList[coins.length + 1][maxSum + 1];
	/*
	 * numCoins 0,0,0,0,0,0 0,1,2,3,4,5 0,1,1,2,2,3 0,1,1,1,2,2 0,1,1,1,1,2
	 *
	 *
	 * usedCoins 0,0,0,0,0,0 0,1,1,1,1,1 0,1,2,{1,2},{2,2},{2,2,1}
	 * 0,1,2,3,{2,2},{2,3} 0,1,2,3,4,{2,3}
	 */

	private int getMinCoinChange() {
		for (int currentCoinIndex = 0; currentCoinIndex < coins.length + 1; currentCoinIndex++) {
			for (int currentAmount = 0; currentAmount < maxSum + 1; currentAmount++) {
				// add an extra row and an extra column and initialize the elements
				if (currentCoinIndex == 0 || currentAmount == 0) {
					numCoins[currentCoinIndex][currentAmount] = 0;
					usedCoins[currentCoinIndex][currentAmount] = new ArrayList<>();
					usedCoins[currentCoinIndex][currentAmount].add(0);
				}
				// if current coin can be used to make currentAmount
				else if (coins[currentCoinIndex - 1] <= currentAmount) {
					int currentCoin = coins[currentCoinIndex - 1];
					int remainder = currentAmount % currentCoin;
					int numberOfCurrentCoin = currentAmount / currentCoin;
					int previousCoinIndex = currentCoinIndex - 1;
					// if previous index has non zero value for the amount
					if (numCoins[previousCoinIndex][currentAmount] > 0) {
						// let's say the currentCoin = 4, currentAmount = 4, number of 4Rs coin required
						// to make 4Rs is 1
						if (remainder == 0 && numberOfCurrentCoin > 0
								&& numberOfCurrentCoin < numCoins[currentCoinIndex - 1][currentAmount]) {
							numCoins[currentCoinIndex][currentAmount] = numberOfCurrentCoin;
							usedCoins[currentCoinIndex][currentAmount] = new ArrayList<>();
							usedCoins[currentCoinIndex][currentAmount].add(currentCoin);
						}
						// let's say the currentCoin = 4, currentAmount = 5, number of 4Rs coin required
						// to make 4Rs is 1 and additional 1Re is required
						else if (numberOfCurrentCoin > 0
								&& numberOfCurrentCoin < numCoins[currentCoinIndex - 1][currentAmount]) {
							/*
							 * number of 4Rs coin needed for 5Rs is 2 number of 1Re coin needed for
							 * additional 1Re is 1,since we already know that as it is stored
							 */
							numCoins[currentCoinIndex][currentAmount] = numberOfCurrentCoin
									+ numCoins[currentCoinIndex - 1][remainder];// ** DP using previous values
							usedCoins[currentCoinIndex][currentAmount] = new ArrayList<>();
							usedCoins[currentCoinIndex][currentAmount].add(currentCoin);
							usedCoins[currentCoinIndex][currentAmount]
									.addAll(usedCoins[currentCoinIndex - 1][remainder]);// ** DP using previous values

						}
					} else if (remainder == 0 && numberOfCurrentCoin > 0) {
						// let's say the currentCoin = 1, currentAmount = 5, number of 1Re coin required
						// to make 5Rs is 5

						numCoins[currentCoinIndex][currentAmount] = numberOfCurrentCoin;
						usedCoins[currentCoinIndex][currentAmount] = new ArrayList<>();
						usedCoins[currentCoinIndex][currentAmount].add(currentCoin);

					}

				}
				/*
				 * if current coin is greater than the currentAmount let's say current coin is 2
				 * and current amount is 1 as we can't use the current coin we would use the
				 * previous coin to add up to the amount In this case we would use 1Re coin to
				 * add up to the current amount
				 */
				else {
					numCoins[currentCoinIndex][currentAmount] = numCoins[currentCoinIndex - 1][currentAmount];
					usedCoins[currentCoinIndex][currentAmount] = usedCoins[currentCoinIndex - 1][currentAmount];
				}

			}
		}
		for (int currentCoinIndex = 0; currentCoinIndex < coins.length + 1; currentCoinIndex++) {
			for (int currentAmount = 0; currentAmount < maxSum + 1; currentAmount++) {
				System.out.print(numCoins[currentCoinIndex][currentAmount] + " ");
			}
			System.out.println();
		}
		for (int currentCoinIndex = 0; currentCoinIndex < coins.length + 1; currentCoinIndex++) {
			for (int currentAmount = 0; currentAmount < maxSum + 1; currentAmount++) {
				System.out.print(usedCoins[currentCoinIndex][currentAmount] + " ");
			}
			System.out.println();
		}
		return numCoins[coins.length][maxSum];
	}

	public static void main(String[] arg) {

		System.out.println(new CoinChange().getMinCoinChange());
	}
}
