class Solution {
    public int largestSubmatrix(final int[][] matrix) {
        final int m = matrix.length;
        final int n = matrix[0].length;
        int largest = 0;
        final int[] height = new int[n];
        final int[] ordered = new int[n];
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (matrix[row][col] == 1) {
                    height[col]++;
                } else {
                    height[col] = 0;
                }
            }
            System.arraycopy(
                height,
                0,
                ordered,
                0,
                n
            );
            Arrays.sort(ordered);
            for (int col = 0; col < n; col++) {
                largest = Math.max(
                    largest,
                    ordered[col] * (n - col)
                );
            }
        }
        return largest;
    }
}