class Solution {
    public int numSquares(int n) {
        int[] memtable = new int[n + 1];
        Arrays.fill(memtable, -1);
        return solve(n, memtable);
    }

    private int solve(final int n, final int[] memtable) {
        int res = memtable[n];
        if (res != -1)
            return res;
        if (n == 0)
            res = 0;
        else if (n == 1)
            res = 1;
        else {
            int min = Integer.MAX_VALUE;
            for (int x = 1; x * x <= n; x++) {
                min = Math.min(min, 1 + solve(n - x * x, memtable));
            }
            res = min;
        }
        memtable[n] = res;
        return res;
    }
}