package Day2.clone;

public class Profit {
	public int maxProfit(int[] prices) {
		int profit = 0;
		int maxprofit = 0;
		int minNo = prices[0];
		for (int i = 0; i < prices.length; i++) {
			if (prices[i] < minNo) {
				minNo = prices[i];
			} else if (prices[i] > minNo) {
				profit = prices[i] - minNo;
			}
			maxprofit = Math.max(profit, maxprofit);
		}
		return maxprofit;
	}
	public static void main(String[] args) {
		Profit d = new Profit();
		int nums[] = new int[] { 1, 2, 3, 3, 6, 7, 8, 8, 8 };
		int k = d.maxProfit(nums);
		System.out.println("profit = " + k);
		
		int nums2[] = new int[] {7,1,5,3,6,4};
		int l = d.maxProfit(nums2);
		System.out.println("profit = " + l);
		
		int nums3[] = new int[] {7,6,4,3,1};
		int m=d.maxProfit(nums3);
		System.out.println("profit = " + m);
	}
}
