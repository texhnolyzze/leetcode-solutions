class Solution {
    public int kthLargestValue(final int[][] matrix, final int k) {
        final int m = matrix.length;
        final int n = matrix[0].length;
        final int total = m * n;
        final int[] values = new int[total];
        int valueIdx = 0;
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                final int elem = matrix[row][col];
                final int value;
                if (row == 0) {
                    if (col != 0) {
                        value = elem ^ matrix[0][col - 1];
                    } else {
                        value = elem;
                    }
                } else {
                    if (col == 0) {
                        value = elem ^ matrix[row - 1][0];
                    } else {
                        value = elem ^ matrix[row - 1][col - 1] ^ matrix[row - 1][col] ^ matrix[row][col - 1];
                    }
                }
                matrix[row][col] = value;
                values[valueIdx++] = value;
            }
        }
        Arrays.sort(values);
        return values[total - k];
    }
}