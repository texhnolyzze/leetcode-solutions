class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length < 2)
            return 0;
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                max = Math.max(max, prices[i] - prices[j]);
            }
        }
        return max <= 0 ? 0 : max;
    }
}