class Solution {
    public int uniquePaths(int m, int n) {
        if (m == 1 && n == 1)
            return 1;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) for (int j = 0; j < n; j++) dp[i][j] = -1;
        dp[0][0] = 0;
        if (m >= 2)
            dp[1][0] = 1;
        if (n >= 2)
            dp[0][1] = 1;
        count(dp, m - 1, n - 1);
        return dp[m - 1][n - 1];
    }

    private static void count(int[][] dp, int y, int x) {
        if (dp[y][x] != -1)
            return;
        int i = 0, j = 0;
        if (y - 1 >= 0) {
            if (dp[y - 1][x] == -1)
                count(dp, y - 1, x);
            i = dp[y - 1][x];
        }
        if (x - 1 >= 0) {
            if (dp[y][x - 1] == -1)
                count(dp, y, x - 1);
            j = dp[y][x - 1];
        }
        dp[y][x] = i + j;
    }
}