class Solution {
    
    public int climbStairs(int n) {
        int[] memo = new int[n + 1];
        java.util.Arrays.fill(memo, -1);
        return climbStairs(n, memo);
    }

    private int climbStairs(int n, int[] memo) {
        if (n < 0)
            return 0;
        if (memo[n] != -1)
            return memo[n];
        int res;
        if (n == 0)
            res = 1;
        else
            res = climbStairs(n - 1, memo) + climbStairs(n - 2, memo);
        memo[n] = res;
        return res;
    }
    
}