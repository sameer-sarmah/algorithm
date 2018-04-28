package dp;

import java.util.ArrayList;
import java.util.List;

public class CoinChange {
	int coins[] = {1,2,3,4 };
	int maxSum = 5;
	int numCoins[][]=new int[coins.length+1][maxSum+1];
	List<Integer>[][] usedCoins=new ArrayList[coins.length+1][maxSum+1];
   /* numCoins
    * 0,0,0,0,0,0 
    * 0,1,2,3,4,5
    * 0,1,1,2,2,3
    * 0,1,1,1,2,2
    * 0,1,1,1,1,2          
    *
    *
    * usedCoins
    * 0,0,0,0,0,0 
    * 0,1,1,1,1,1
    * 0,1,2,{1,2},{2,2},{2,2,1}
    * 0,1,2,3,{2,2},{2,3}
    * 0,1,2,3,4,{2,3}
    * */
	
	
	private int getMinCoinChange() {
		for (int i = 0; i < coins.length+1; i++) {
			for (int j = 0; j < maxSum+1; j++) {
				if (i == 0) {
					numCoins[i][j] = 0;
					usedCoins[i][j]=new ArrayList<>();
					usedCoins[i][j].add(0);
				} else if (j == 0) {
					numCoins[i][j] = 0;
					usedCoins[i][j]=new ArrayList<>();
					usedCoins[i][j].add(0);
				} 
				else if(coins[i-1]<=j)
				{
					int coin=coins[i-1];
					int remainder=j%coin;
				
					int num=j/coin;
					if(numCoins[i-1][j]>0){
					if(remainder==0 && num>0 &&num<numCoins[i-1][j])
					{
						numCoins[i][j] = num;
						usedCoins[i][j]=new ArrayList<>();
						usedCoins[i][j].add(coin);
					}
					else if(num>0 && num<numCoins[i-1][j])
					{
						
						numCoins[i][j] = num+numCoins[i-1][remainder];//** DP using previous values
						usedCoins[i][j]=new ArrayList<>();
						usedCoins[i][j].add(coin);
						usedCoins[i][j].addAll(usedCoins[i-1][remainder]);//** DP using previous values
						
					}
					}
					else
					{
						if(remainder==0 && num>0)
						{
							numCoins[i][j] = num;
							usedCoins[i][j]=new ArrayList<>();
							usedCoins[i][j].add(coin);
						}
						else if(num>0)
						{
							
							numCoins[i][j] = num+numCoins[i-1][remainder];//** DP using previous values
							usedCoins[i][j]=new ArrayList<>();
							usedCoins[i][j].add(coin);
							usedCoins[i][j].addAll(usedCoins[i-1][remainder]);//** DP using previous values
							
						}
					}
					
				}
				else
				{
					numCoins[i][j]=numCoins[i-1][j];
					usedCoins[i][j]=usedCoins[i-1][j];
				}

			}
		}
		for (int i = 0; i < coins.length+1; i++) {
			for (int j = 0; j < maxSum+1; j++) {
				System.out.print(numCoins[i][j]+" ");
			}
			System.out.println();
		}
		for (int i = 0; i < coins.length+1; i++) {
			for (int j = 0; j < maxSum+1; j++) {
				System.out.print(usedCoins[i][j]+" ");
			}
			System.out.println();
		}
		return numCoins[coins.length][maxSum];
	}

	public static void main(String[] arg) {
     
      System.out.println( new CoinChange().getMinCoinChange());
	}
}
