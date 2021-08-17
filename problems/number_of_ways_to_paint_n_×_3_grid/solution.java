class Solution {
    private final int numColors = 3;

    private static final int MOD = (int) (1E9 + 7);

    public int numOfWays(final int n) {
        int res = 0;
        final int[][] memtable = new int[numColors * numColors * numColors][n];
        for (int leftColor = 0; leftColor < numColors; leftColor++) {
            for (int midColor = 0; midColor < numColors; midColor++) {
                if (midColor == leftColor) {
                    continue;
                }
                for (int rightColor = 0; rightColor < numColors; rightColor++) {
                    if (rightColor == midColor) {
                        continue;
                    }
                    res = (res + numOfWays(leftColor, midColor, rightColor, n - 1, memtable)) % MOD;
                }
            }
        }
        return res;
    }

    private int numOfWays(
        final int prevLeftColor,
        final int prevMidColor,
        final int prevRightColor,
        final int n,
        final int[][] memtable
    ) {
        if (n == 0) {
            return 1;
        }
        final int index = (prevRightColor * numColors * numColors) + (prevMidColor * numColors) + prevLeftColor;
        int res = memtable[index][n - 1];
        if (res != 0) {
            return res;
        }
        for (int leftColor = 0; leftColor < numColors; leftColor++) {
            if (leftColor == prevLeftColor) {
                continue;
            }
            for (int midColor = 0; midColor < numColors; midColor++) {
                if (midColor == prevMidColor || midColor == leftColor) {
                    continue;
                }
                for (int rightColor = 0; rightColor < numColors; rightColor++) {
                    if (rightColor == prevRightColor || rightColor == midColor) {
                        continue;
                    }
                    res = (res + numOfWays(leftColor, midColor, rightColor, n - 1, memtable)) % MOD;
                }
            }
        }
        memtable[index][n - 1] = res;
        return res;
    }
}