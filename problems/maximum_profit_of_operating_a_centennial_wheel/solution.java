class Solution {
    public int minOperationsMaxProfit(
        final int[] customers,
        final int boardingCost,
        final int runningCost
    ) {
        int profit = 0;
        int rotations = 0;
        int customersWaiting = 0;
        int highestProfit = 0;
        int highestProfitRotations = -1;
        int i = 0;
        while (customersWaiting > 0 || i < customers.length) {
            if (i < customers.length)
                customersWaiting += customers[i++];
            rotations++;
            final int customersServed = Math.min(4, customersWaiting);
            profit = profit + customersServed * boardingCost - runningCost;
            if (profit > highestProfit) {
                highestProfit = profit;
                highestProfitRotations = rotations;
            }
            customersWaiting -= customersServed;
        }
        return highestProfitRotations;
    }
}