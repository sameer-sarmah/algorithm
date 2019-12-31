package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
/*
 * All possible combination
 * */
public class CoinChangeWays {
 public static void main(String[] args) {
	 List<Integer> denominations = Arrays.asList(1, 5, 10, 15, 25);
		Map<Integer, List<List<CoinInfo>>> cachedResult = new HashMap<>();
		for(List<CoinInfo> deno : findDenominations(6, denominations,cachedResult)) {
			System.out.println(deno);
		}
		
}

private static  List<List<CoinInfo>> findDenominations(int queryAmount, List<Integer> denominations,
		Map<Integer, List<List<CoinInfo>>> cachedResult) {
	    for(int amount=1;amount <=queryAmount;amount++ ) {
			for (int denomination : denominations) {

				if (denomination <= amount) {
					int numberOfCoinsOfThisDenominationRequired = amount / denomination;
					//pre 
					for (int numberOfthisDenomination = 1; numberOfthisDenomination <= numberOfCoinsOfThisDenominationRequired; numberOfthisDenomination++) {
						int remainingAmount = (amount - (numberOfthisDenomination * denomination));
						CoinInfo thisDenomination = new CoinInfo(denomination, numberOfthisDenomination);
						List<List<CoinInfo>> coinChangePermutations=new ArrayList<>();
						List<CoinInfo> coinsList = new ArrayList<>();
						coinsList.add(thisDenomination);
						
						if (remainingAmount > 0) {
							if (cachedResult.get(remainingAmount) != null) {
								for(List<CoinInfo> denominationList : cachedResult.get(remainingAmount)) {
									coinChangePermutations.add(merge(coinsList, denominationList));	
								}
							} 
						}else {
							coinChangePermutations.add(coinsList);
						}
						
						if (cachedResult.get(amount) == null) {
							cachedResult.put(amount, coinChangePermutations);
						} else {
							/*
						*************amount 2 

						intermediate=1;
						remainder=1
						coins={[coin=1,count=1]}
						remainingCoins={[coin=1,count=1]}
						cache [1={[coin=1,count=1]},2={[coin=1,count=2]}]
						
						intermediate=2
						remainder=0
						coins={[coin=1,count=2]}
						remainingCoins={}
						cache [1={[coin=1,count=1]},2={[coin=1,count=2]}]
						cache(2)={[coin=1,count=2]}
						removeDuplicate(coins,cache(2))={[coin=1,count=2]}


						*************amount 6
						
						intermediate=1;
						remainder=5
						coins={[coin=1,count=1]}
						remainingCoins={[coin=1,count=5],[coin=5,count=1]}
						cache [1={[coin=1,count=1]},2={[coin=1,count=2]},...,5={[coin=1,count=5],[coin=5,count=1]},6={[coin=1,count=5],[coin=5,count=1]}]
						
						intermediate=2
						remainder=4
						coins={[coin=1,count=2]}
						remainingCoins={[coin=1,count=4]}
						coins=merge(coins,remainingCoins)={[coin=1,count=6]}
						cache [1={[coin=1,count=1]},2={[coin=1,count=2]},...,5={[coin=1,count=5],[coin=5,count=1]},6={[coin=1,count=5],[coin=5,count=1]}]
						cache(6)={[coin=1,count=5],[coin=5,count=1]}
						
						removeDuplicate(coins,cache(6))={[coin=1,count=5],[coin=5,count=1]},{[coin=1,count=6]}
							 
							 
							 * */
							//5=[[CoinInfo [denomination=1, count=5], CoinInfo [denomination=5, count=1]]]
							List<List<CoinInfo>> cachedList=cachedResult.get(amount);
							List<List<CoinInfo>> list=new ArrayList<>();
							for(List<CoinInfo> denominationList : coinChangePermutations) {
								for(List<CoinInfo> denominationListFromCache : cachedList) {
									list.add(denominationListFromCache);	
									List<CoinInfo>  validatedList=addIfDoesnotExist(denominationListFromCache,denominationList);
									if(!validatedList.isEmpty()) {
									list.add(validatedList);	
									}
									else {
										break;
									}
								}
								
								
							}
							cachedResult.put(amount,list);
						}
					}

				}

			}
	    }
		return cachedResult.get(queryAmount);
}

/*
 *  
 * list1 [{coin=1,count=1}] ,list2 [{coin=1,count=5},{coin=5,count=1}] 
 * should produce [{coin=1,count=6}] and [{coin=1,count=1},{coin=5,count=1}]
 * 
 * */
	public static List<List<CoinInfo>> consolidatedList(List<CoinInfo> list1, List<CoinInfo> list2) {
		List<List<CoinInfo>> merged = new ArrayList<>();
		for (CoinInfo coin1 : list1) {
			for (CoinInfo coin2 : list2) {
				List<CoinInfo> l1=new ArrayList<>();
				l1.add(coin1);
				List<CoinInfo> l2=new ArrayList<>();
				l2.add(coin2);
				merged.add(merge(l1, l2));
			}
		
		}
		return merged;
	}
	/*
	 *  
	 * list1 [{coin=1,count=1}] ,list2 [{coin=1,count=5}] 
	 * should produce [{coin=1,count=6}] 
	 * 
	 * */
	public static List<CoinInfo> merge(List<CoinInfo> list1, List<CoinInfo> list2) {
		List<CoinInfo> merged = new ArrayList<>();
		for (CoinInfo coin1 : list1) {
			int coinDenomination = coin1.getCoin();
			int count = coin1.getCount();
			for (CoinInfo coin2 : list2) {
				if (coinDenomination == coin2.getCoin()) {
					count += coin2.getCount();
				}
				else {
					merged.add(coin2);	
				}
				
				
			}
			merged.add(new CoinInfo(coinDenomination, count));	
		}
		return merged;
	}
	/*
	 *  
	 * list1 [[coin=1,count=1]] ,list2 [[coin=1,count=1]] 
	 * should produce [{coin=1,count=1}] 
	 * 
	 * list1 [[coin=1,count=6]] ,list2 [[coin=1,count=1],[coin=5,count=1]] 
	 * should produce [[coin=1,count=1],[coin=5,count=1]] 
	 * 
	 * */
	public static List<CoinInfo> addIfDoesnotExist(List<CoinInfo> primaryList, List<CoinInfo> validatingList) {
		Set<CoinInfo> merged = new HashSet<>();
		for (CoinInfo coin : validatingList) {
			if(!primaryList.contains(coin)) {
				merged.add(coin);	
			}
		}
		return new ArrayList<CoinInfo>(merged);
	}
}
