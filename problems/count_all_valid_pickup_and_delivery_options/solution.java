class Solution {
    
    private static final int MOD = 1000000007;
    
    private int[] fact;

    public int countOrders(final int n) {
        this.fact = new int[n + 1];
        final int[][] memtable = new int[n + 1][n + 1];
        final int res = countOrders(0, n, memtable);
        return res;
    }

    private int countOrders(
        final int alreadyPicked,
        final int remainedToPickup,
        final int[][] memtable
    ) {
        int res = memtable[alreadyPicked][remainedToPickup];
        if (res != 0)
            return res;
        if (remainedToPickup == 0) {
            res = fact(alreadyPicked);
        } else {
            if (alreadyPicked == 0) {
                res = (int) mulmod(remainedToPickup, countOrders(1, remainedToPickup - 1, memtable));
            } else {
                res = (int) ((
                    mulmod(alreadyPicked, countOrders(alreadyPicked - 1, remainedToPickup, memtable)) +
                    mulmod(remainedToPickup, countOrders(alreadyPicked + 1, remainedToPickup - 1, memtable))
                ) % MOD);
            }
        }
        memtable[alreadyPicked][remainedToPickup] = res;
        return res;
    }

    private int fact(int n) {
        if (n == 0)
            return 1;
        int res = fact[n];
        if (res != 0) {
            return res;
        }
        res = (int) mulmod(n, fact(n - 1));
        fact[n] = res;
        return res;
    }

    private long mulmod(
        long a,
        long b
    ) {
        long res = 0;
        a = a % MOD;
        while (b > 0) {
            if (b % 2 == 1) {
                res = (res + a) % MOD;
            }
            a = (a * 2) % MOD;
            b /= 2;
        }
        return res %  MOD;
    }
    
}