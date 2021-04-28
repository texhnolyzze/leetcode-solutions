class Solution {
    public int maxProfit(int k, int[] prices) {
        if (prices.length <= 1)
            return 0;
        int[][][] memtable = new int[2 * k + 1][prices.length][2];
        for (int i = 0; i < memtable.length; i++) {
            for (int j = 0; j < memtable[i].length; j++) {
                Arrays.fill(memtable[i][j], -1);
            }
        }
        return maxProfit(2 * k, prices, 0, false, memtable);
    }

    private int maxProfit(
        int numOperationsAllowed,
        int[] prices,
        int day,
        boolean stocked,
        int[][][] memtable
    ) {
        if (numOperationsAllowed == 0)
            return 0;
        else {
            int stockedIdx = stocked ? 1 : 0;
            int result = memtable[numOperationsAllowed][day][stockedIdx];
            if (result != -1)
                return result;
            if (day == prices.length - 1) {
                if (stocked) {
                    result = prices[day];
                } else
                    result = 0;
            } else {
                if (stocked) {
                    int profitIfSell = prices[day] + maxProfit(numOperationsAllowed - 1, prices, day + 1, false, memtable);
                    int profitIfNotSell = maxProfit(numOperationsAllowed, prices, day + 1, true, memtable);
                    result = Math.max(profitIfSell, profitIfNotSell);
                } else {
                    int profitIfBuy = -prices[day] + maxProfit(numOperationsAllowed - 1, prices, day + 1, true, memtable);
                    int profitIfNotBuy = maxProfit(numOperationsAllowed, prices, day + 1, false, memtable);
                    result = Math.max(profitIfBuy, profitIfNotBuy);
                }
            }
            memtable[numOperationsAllowed][day][stockedIdx] = result;
            return result;
        }
    }
}