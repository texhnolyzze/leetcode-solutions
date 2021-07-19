class Solution {
    public int coinChange(
        final int[] coins,
        final int amount
    ) {
        final int[] min = new int[amount + 1];
        return coinChange(coins, min, amount);
    }

    private int coinChange(
        final int[] coins,
        final int[] min,
        final int amount
    ) {
        if (amount == 0) {
            return 0;
        }
        int res = min[amount];
        if (res != 0) {
            return res;
        }
        res = Integer.MAX_VALUE;
        for (final int coin : coins) {
            final int diff = amount - coin;
            if (diff >= 0) {
                final int sub = coinChange(coins, min, diff);
                if (sub != -1) {
                    res = Math.min(res, 1 + sub);
                }
            }
        }
        if (res == Integer.MAX_VALUE) {
            res = -1;
        }
        min[amount] = res;
        return res;
    }
}