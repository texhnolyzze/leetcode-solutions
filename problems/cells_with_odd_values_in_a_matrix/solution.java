class Solution {
    public int oddCells(
        final int m,
        final int n,
        final int[][] indices
    ) {
        final int[] rows = new int[m];
        final int[] cols = new int[n];
        int oddCols = 0;
        int oddRows = 0;
        for (final int[] op : indices) {
            int row = ++rows[op[0]];
            if (row % 2 != 0) {
                oddRows++;
            } else {
                oddRows--;
            }
            final int col = ++cols[op[1]];
            if (col % 2 != 0) {
                oddCols++;
            } else {
                oddCols--;
            }
        }
        int res = 0;
        for (final int row : rows) {
            if (row % 2 != 0) {
                res += n - oddCols;
            }
        }
        for (final int col : cols) {
            if (col % 2 != 0) {
                res += m - oddRows;
            }
        }
        return res;
    }
}