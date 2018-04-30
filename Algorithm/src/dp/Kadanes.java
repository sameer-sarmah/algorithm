package dp;

public class Kadanes {
	public static void main(String[] args) {
		int[] numbers = { -2, -3, 4, -1, -2, 1, 5, -3 };
		int maxSumGlobal = 0, maxSumLocal = 0;
		for (int number : numbers) {
			if (maxSumLocal + number > 0) {
				maxSumLocal = maxSumLocal + number;
			}
			if (maxSumLocal > maxSumGlobal) {
				maxSumGlobal = maxSumLocal;
			}
		}
		System.out.println(maxSumGlobal);
	}
}
