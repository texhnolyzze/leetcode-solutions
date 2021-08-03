class Solution {
    public int maxProfit(final int[] prices) {
        final int[][] memtable = new int[2][prices.length];
        Arrays.fill(memtable[0], Integer.MIN_VALUE);
        Arrays.fill(memtable[1], Integer.MIN_VALUE);
        return maxProfit(prices, 0, false, memtable);
    }

    private int maxProfit(
        final int[] prices,
        final int idx,
        final boolean inStock,
        final int[][] memtable
    ) {
        if (idx >= prices.length) {
            return 0;
        }
        int res = memtable[inStock ? 1 : 0][idx];
        if (res != Integer.MIN_VALUE) {
            return res;
        }
        if (inStock) {
            res = Math.max(
                prices[idx] + maxProfit(prices, idx + 1, false, memtable),
                maxProfit(prices, idx + 1, true, memtable)
            );
        } else {
            res = Math.max(
                -prices[idx] + maxProfit(prices, idx + 1, true, memtable),
                maxProfit(prices, idx + 1, false, memtable)
            );
        }
        memtable[inStock ? 1 : 0][idx] = res;
        return res;
    }
}