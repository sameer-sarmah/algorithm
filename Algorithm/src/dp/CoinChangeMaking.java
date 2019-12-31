package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//this doesnot pre compute and pre populate the cache but the cache is populated on demand 
public class CoinChangeMaking {

	public static void main(String[] args) {
		// let's assume that the current denominations available are 1,5,10,15,25
		List<Integer> denominations = Arrays.asList(1, 5, 10, 15, 25);
		System.out.println(findDenominations(6,denominations));
		
	}

	public static List<CoinInfo> findDenominations(int amount, List<Integer> denominations) {
		Map<Integer, List<CoinInfo>> amountToCoinInfos = new HashMap<>();
		for (int denomination : denominations) {
			
			if (denomination <= amount) {
				int remainingAmount = amount % denomination;
				int numberOfCoinsOfThisDenominationRequired = amount / denomination;
				CoinInfo thisDenomination = new CoinInfo(denomination, numberOfCoinsOfThisDenominationRequired);
				List<CoinInfo> coins = new ArrayList<>();
				coins.add(thisDenomination);
				
				// let's say the currentCoin = 4, currentAmount = 5, number of 4Rs coin required
				// to make 4Rs is 1 and additional 1Re is required
				if(remainingAmount>0) {
					//if the coins required to add up to 1Re is present in the cache i.e amountToCoinInfos we use the same
					if (amountToCoinInfos.get(remainingAmount) != null) {
						coins.addAll(amountToCoinInfos.get(remainingAmount));
					}
					//if the coins required to add up to 1Re is not present in the cache i.e amountToCoinInfos we calculate it and update the cache for the amount 
					else {
						List<CoinInfo> remainingAmountCoinList=findDenominations(remainingAmount,denominations);
						coins.addAll(remainingAmountCoinList);
						amountToCoinInfos.put(remainingAmount, remainingAmountCoinList);
					}	
				}
				// let's say the currentCoin = 1, currentAmount = 5, number of 1Re coin required to make 5Rs is 5
				if (amountToCoinInfos.get(amount) == null) {
					amountToCoinInfos.put(amount, coins);
				} 
				// let's say the currentCoin = 4, currentAmount = 5, number of 4Rs coin required
				// to make 4Rs is 1 and additional 1Re is required
				else {
					int totalCoins = numberOfCoinsOfThisDenominationRequired;
					List<CoinInfo> remainingAmountCoins = amountToCoinInfos.get(remainingAmount);
					if (remainingAmount>0 && remainingAmountCoins != null) {
						totalCoins += totalCoinsFromCoinList(remainingAmountCoins);
					}
					/*lets say the amount is 11,and let's say amountToCoinInfos has value (coin = 1Re,number = 11) as coin info for amount 11 
					 *when the current coin is Rs5 then we have the calculated coin info as  {(coin = 1Re,number = 1),(coin = 5Rs,number = 2)}
					 *since the total coin count with 5Rs coin is 3 which is lesser than 11 with 1Re coin we update the cache amountToCoinInfos
					 * */
					if (totalCoins < totalCoinsFromCoinList(amountToCoinInfos.get(amount))) {
						amountToCoinInfos.put(amount, coins);
					}

				}

			}
			
			
		}
		return amountToCoinInfos.get(amount);
	}

	public static int totalCoinsFromCoinList(List<CoinInfo> coinInfoList) {
		int count = 0;
		for (CoinInfo coins : coinInfoList) {
			count += coins.getCount();
		}
		return count;
	}
}

class CoinInfo {
	private int coin, count;

	public CoinInfo(int coin, int count) {
		super();
		this.coin = coin;
		this.count = count;
	}

	public int getCoin() {
		return coin;
	}

	public int getCount() {
		return count;
	}

	@Override
	public String toString() {
		return "CoinInfo [denomination=" + coin + ", count=" + count + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + coin;
		result = prime * result + count;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CoinInfo other = (CoinInfo) obj;
		if (coin != other.coin)
			return false;
		if (count != other.count)
			return false;
		return true;
	}
	
	
}
