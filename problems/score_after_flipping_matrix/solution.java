class Solution {
    public static int matrixScore(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        for (int[] row : mat) {
            if (row[0] == 0) {
                row[0] = 1;
                for (int j = 1; j < m; j++) {
                    row[j] ^= 1;
                }
            }
        }
        for (int i = m - 1; i >= 1; i--) {
            int numZeros = 0;
            for (int j = 0; j < n; j++) 
                numZeros += (1 ^ (mat[j][i] & 1));
            if (numZeros > n / 2) {
                for (int j = 0; j < n; j++)
                    mat[j][i] ^= 1;
            }
        }
        int matrixScore = 0;
        for (int[] row : mat) {
            int rowAsNum = 0;
            for (int i = 0; i < m; i++) {
                rowAsNum <<= 1;
                rowAsNum |= row[i];
            }
            matrixScore += rowAsNum;
        }
        return matrixScore;
    }
}