class Solution {
    public int minSteps(final int n) {
        final int[][] memtable = new int[n][n];
        return minSteps(n - 1, 0, memtable);
    }

    private int minSteps(
        final int n,
        final int buffer,
        final int[][] memtable
    ) {
        if (n == 0) {
            return 0;
        } else if (n < 0 || n - buffer < 0) {
            return Integer.MAX_VALUE;
        }
        int res = memtable[n - 1][buffer];
        if (res != 0) {
            return res;
        }
        final int ifPasteBuffer;
        if (buffer > 0) {
            final int sub = minSteps(n - buffer, buffer, memtable);
            if (sub != Integer.MAX_VALUE) {
                ifPasteBuffer = 1 + sub;
            } else {
                ifPasteBuffer = Integer.MAX_VALUE;
            }
        } else {
            ifPasteBuffer = Integer.MAX_VALUE;
        }
        final int ifCopyPasteOnScreen;
        final int sub = minSteps(n - (memtable.length - n), memtable.length - n, memtable);
        if (sub != Integer.MAX_VALUE) {
            ifCopyPasteOnScreen = 2 + sub;
        } else {
            ifCopyPasteOnScreen = Integer.MAX_VALUE;
        }
        res = Math.min(ifPasteBuffer, ifCopyPasteOnScreen);
        memtable[n - 1][buffer] = res;
        return res;
    }
}