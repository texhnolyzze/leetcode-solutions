class Solution {
    public static int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] matrix = new int[m + 1][n + 1];
        for (int i = 1; i <= n; i++)
            matrix[0][i] = i;
        for (int i = 1; i <= m; i++)
            matrix[i][0] = i;
        for (int i = 1; i <= m; i++) {
            char c1 = word1.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                char c2 = word2.charAt(j - 1);
                if (c1 == c2) {
                    matrix[i][j] = matrix[i - 1][j - 1];
                } else {
                    matrix[i][j] = Math.min(matrix[i - 1][j], matrix[i][j - 1]) + 1;
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i - 1][j - 1] + 1);
                }
            }
        }
        return matrix[m][n];
    }
}