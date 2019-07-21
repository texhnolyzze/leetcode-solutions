class Solution {
    public static int minFallingPathSum(int[][] a) {
        int n = a.length;
        int[][] dp = new int[n][n];
        System.arraycopy(a[0], 0, dp[0], 0, n);
        for (int i = 1; i < n; i++)
            nextRow(a, dp, i);
        int minSum = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++)
            minSum = Math.min(minSum, dp[n - 1][i]);
        return minSum;
    }

    private static void nextRow(int[][] a, int[][] dp, int row) {
        int n = a.length;
        dp[row][0] = Math.min(dp[row - 1][0], dp[row - 1][1]) + a[row][0];
        for (int i = 1; i < n - 1; i++) {
            dp[row][i] = Math.min(dp[row - 1][i - 1], dp[row - 1][i + 1]);
            dp[row][i] = Math.min(dp[row][i], dp[row - 1][i]) + a[row][i];
        }
        dp[row][n - 1] = Math.min(dp[row - 1][n - 1], dp[row - 1][n - 2]) + a[row][n - 1];
    }
}