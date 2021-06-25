class Solution {

    private static final int MOD = 1000000007;

    private int[][] pow;

    public int numOfArrays(
        final int n,
        final int m,
        final int k
    ) {
        pow = new int[m + 1][n + 1];
        final int[][][] memtable = new int[m + 1][n + 1][k + 1];
        for (int i = 0; i < memtable.length; i++) {
            for (int j = 0; j < memtable[i].length; j++) {
                Arrays.fill(memtable[i][j], -1);
            }
        }
        int res = 0;
        for (int maxSoFar = 1; maxSoFar <= m - k + 1; maxSoFar++) {
            res = (res + numOfArrays0(n - 1, k - 1, maxSoFar, memtable)) % MOD;
        }
        return res;
    }

    private int numOfArrays0(
        final int n,
        final int k,
        final int maxSoFar,
        final int[][][] memtable
    ) {
        int res = memtable[maxSoFar][n][k];
        if (res != -1) {
            return res;
        }
        res = 0;
        final int m = memtable.length - 1;
        if (k == 0) {
            res = pow(maxSoFar, n);
        } else {
            if (n > k) {
                for (int i = 1; i <= maxSoFar; i++) {
                    res = (res + numOfArrays0(n - 1, k, maxSoFar, memtable)) % MOD;
                }
            }
            for (int i = maxSoFar + 1; i <= m - k + 1; i++) {
                res = (res + numOfArrays0(n - 1, k - 1, i, memtable)) % MOD;
            }
        }
        memtable[maxSoFar][n][k] = res;
        return res;
    }

    private int pow(final int m, final int n) {
        if (n == 0) {
            return 1;
        }
        int res = pow[m][n];
        if (res != 0) {
            return res;
        }
        res = (int) (((long) m * pow(m, n - 1)) % MOD);
        pow[m][n] = res;
        return res;
    }
    
}