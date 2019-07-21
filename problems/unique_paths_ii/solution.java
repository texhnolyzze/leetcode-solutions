class Solution {
    private int[][] dp;
    private int[][] obstacleGrid;
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        this.obstacleGrid = obstacleGrid;
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        this.dp = new int[m][n];
        for (int i = 0; i < m; i++) for (int j = 0; j < n; j++) dp[i][j] = -1;
        if (obstacleGrid[0][0] != 1) {
            if (m == 1 && n == 1)
                return 1;
            dp[0][0] = 0;
        } else
            return 0;
        if (m >= 2) {
            if (obstacleGrid[1][0] != 1)
                dp[1][0] = 1;
            else
                dp[1][0] = 0;
        }
        if (n >= 2) {
            if (obstacleGrid[0][1] != 1)
                dp[0][1] = 1;
            else
                dp[0][1] = 0;
        }
        count(m - 1, n - 1);
        return dp[m - 1][n - 1] == -1 ? 0 : dp[m - 1][n - 1];
    }
    
    private void count(int y, int x) {
        if (dp[y][x] != -1)
            return;
        if (obstacleGrid[y][x] == 1) {
            dp[y][x] = 0;
            return;
        }
        int i = 0, j = 0;
        if (y - 1 >= 0) {
            count(y - 1, x);
            i = dp[y - 1][x];
        }
        if (x - 1 >= 0) {
            count(y, x - 1);
            j = dp[y][x - 1];
        }
        dp[y][x] = i + j;
    }
}