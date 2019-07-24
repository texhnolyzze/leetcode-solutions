class Solution {
    public static int rob(int[] houses) {
        if (houses.length == 0)
            return 0;
        int[][] dp = new int[2][houses.length];
        Arrays.fill(dp[0], -1);
        Arrays.fill(dp[1], -1);
        rob(houses, dp, houses.length - 1, 0);
        rob(houses, dp, houses.length - 1, 1);
        return Math.max(dp[0][houses.length - 1], dp[1][houses.length - 1]);
    }

    private static int rob(int[] houses, int[][] dp, int i, int j) {
        if (i < 0)
            return 0;
        if (dp[j][i] != -1)
            return dp[j][i];
        int n1 = rob(houses, dp, i - 1, 1);
        int n2 = houses[i] + rob(houses, dp, i - 1, 0);
        dp[0][i] = n1;
        dp[1][i] = n2;
        return Math.max(n1, n2);
    }
}