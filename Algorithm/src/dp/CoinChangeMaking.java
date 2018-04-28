package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoinChangeMaking {

	public static void main(String[] args) {
		// let's assume that the current denominations available are 1,5,10,15,25
		List<Integer> denominations = Arrays.asList(1, 5, 10, 15, 25);
		System.out.println(findDenominations(6,denominations));
		
	}

	public static List<CoinInfo> findDenominations(int amount, List<Integer> denominations) {
		Map<Integer, List<CoinInfo>> cachedResult = new HashMap<>();
		for (int denomination : denominations) {
			
			if (denomination <= amount) {
				int remainingAmount = amount % denomination;
				int numberOfCoinsOfThisDenominationRequired = amount / denomination;
				CoinInfo thisDenomination = new CoinInfo(denomination, numberOfCoinsOfThisDenominationRequired);
				List<CoinInfo> coins = new ArrayList<>();
				coins.add(thisDenomination);
				if(remainingAmount>0) {
					if (cachedResult.get(remainingAmount) != null) {
						coins.addAll(cachedResult.get(remainingAmount));
					}
					else {
						List<CoinInfo> remainingAmountCoinList=findDenominations(remainingAmount,denominations);
						coins.addAll(remainingAmountCoinList);
						cachedResult.put(remainingAmount, remainingAmountCoinList);
					}	
				}
				
				if (cachedResult.get(amount) == null) {
					cachedResult.put(amount, coins);
				} else {
					int totalCoins = numberOfCoinsOfThisDenominationRequired;
					List<CoinInfo> remainingAmountCoins = cachedResult.get(remainingAmount);
					if (remainingAmount>0 && remainingAmountCoins != null) {
						totalCoins += totalCoinsFromCoinList(remainingAmountCoins);
					}

					if (totalCoins < totalCoinsFromCoinList(cachedResult.get(amount))) {
						cachedResult.put(amount, coins);
					}

				}

			}
			
			
		}
		return cachedResult.get(amount);
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
