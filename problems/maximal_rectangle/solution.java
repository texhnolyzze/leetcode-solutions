class Solution {
    public int maximalRectangle(final char[][] matrix) {
        if (matrix.length == 0)
            return 0;
        final int rows = matrix.length;
        final int cols = matrix[0].length;
        final int[][] right = new int[rows][cols];
        final int[][] bottom = new int[rows][cols];
        for (int y = 0; y < rows; y++) {
            for (int x = cols - 1, count = 0; x >= 0; x--) {
                if (matrix[y][x] == '1') {
                    count++;
                } else {
                    count = 0;
                }
                right[y][x] = count;
            }
        }
        for (int x = 0; x < cols; x++) {
            for (int y = rows - 1, count = 0; y >= 0; y--) {
                if (matrix[y][x] == '1') {
                    count++;
                } else {
                    count = 0;
                }
                bottom[y][x] = count;
            }
        }
        int max = 0;
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                if (matrix[y][x] == '1') {
                    int horSide = right[y][x];
                    int verSide = bottom[y][x];
                    max = Math.max(max, horSide);
                    max = Math.max(max, verSide);
                    int diagY = y + 1;
                    int diagX = x + 1;
                    while (diagY < rows && diagX < cols) {
                        if (
                            matrix[diagY][diagX] == '0' ||
                            !contains(x, x + horSide - 1, diagX) ||
                            !contains(y, y + verSide - 1, diagY)
                        ) {
                            break;
                        }
                        final int newHorSide = Math.min(horSide, (diagY - y) + right[diagY][diagX]);
                        final int newVerSide = Math.min(verSide, (diagX - x) + bottom[diagY][diagX]);
                        max = Math.max(max, (diagY - y + 1) * newHorSide);
                        max = Math.max(max, (diagX - x + 1) * newVerSide);
                        horSide = newHorSide;
                        verSide = newVerSide;
                        diagX++;
                        diagY++;
                    }
                }
            }
        }
        return max;
    }

    private boolean contains(final int a, final int b, final int p) {
        return a <= p && p <= b;
    }
}