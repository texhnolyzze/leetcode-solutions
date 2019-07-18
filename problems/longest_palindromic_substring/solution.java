class Solution {
    public static String longestPalindrome(String s) {
        if (s.isEmpty() || s.length() < 2)
            return s;
        int[][] dp = new int[s.length() - 1][];
        for (int i = 0; i < dp.length; i++)
            dp[i] = new int[s.length() - i - 1];
        int max = -1;
        int left = -1, right = -1;
        for (int n = 2; n < s.length(); n++) {
            for (int i = 0; i + n - 1 < s.length(); i++) {
                int j = i + n - 1;
                if (isPalindrome(s, i, j, dp)) {
                    if (max < j - i + 1) {
                        max = j - i + 1;
                        left = i;
                        right = j;
                    }
                }
            }
        }
        if (isPalindrome(s, 0, s.length() - 1, dp))
            return s;
        return max == -1 ? Character.toString(s.charAt(0)) : s.substring(left, right + 1);

    }

    private static boolean isPalindrome(String s, int i, int j, int[][] dp) {
        int len = j - i + 1;
        if (len == 2) {
            if (s.charAt(i) == s.charAt(j)) {
                dp[0][i] = 2;
                return true;
            } else {
                dp[0][i] = 1;
                return false;
            }
        }
        int n = dp[len - 3][i + 1];
        boolean b = s.charAt(i) == s.charAt(j) && (len - 4 >= 0 ? dp[len - 4][i + 1] == len - 2 : true);
        dp[len - 2][i] = b ? len : dp[len - 3][i];
        return b;
    }
}