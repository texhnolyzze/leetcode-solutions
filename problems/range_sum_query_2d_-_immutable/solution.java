class NumMatrix {

        private final int m, n;
        private final int[][] cache;
        public NumMatrix(int[][] matrix) {
            this.m = matrix.length;
            this.n = m == 0 ? 0 : matrix[0].length;
            this.cache = new int[m][n];
            calculateCache(matrix);
        }
        
        private void calculateCache(int[][] matrix) {
            if (m == 0 || n == 0)
                return;
            cache[0][0] = matrix[0][0];
            for (int i = 1; i < n; i++)
                cache[0][i] = matrix[0][i] + cache[0][i - 1];
            for (int i = 1; i < m; i++)
                cache[i][0] = matrix[i][0] + cache[i - 1][0];
            for (int y = 1; y < m; y++) {
                for (int x = 1; x < n; x++) {
                    cache[y][x] = matrix[y][x] + cache[y][x - 1] + cache[y - 1][x] - cache[y - 1][x - 1];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            if (m == 0 || n == 0)
                return -1;
            int a = cache[row2][col2];
            int b = row1 - 1 >= 0 ? cache[row1 - 1][col2] : 0;
            int c = col1 - 1 >= 0 ? cache[row2][col1 - 1] : 0;
            int d = (row1 - 1 >= 0 && col1 - 1 >= 0) ? cache[row1 - 1][col1 - 1] : 0;
            return a - (b + c) + d;
        }
        
    }
/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */