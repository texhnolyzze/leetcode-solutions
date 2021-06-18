class Solution {
    public int getMoneyAmount(final int n) {
        final int[][] memtable = new int[n + 1][n + 1];
        for (int i = 0; i < memtable.length; i++) {
            Arrays.fill(memtable[i], Integer.MAX_VALUE);
        }
        return getMoneyAmount(1, n, memtable);
    }

    private int getMoneyAmount(final int left, final int right, final int[][] memtable) {
        if (left >= right) {
            return 0;
        }
        int min = memtable[left][right];
        if (min != Integer.MAX_VALUE)
            return min;
        for (int i = left; i <= right; i++) {
            final int leftRes = i + getMoneyAmount(left, i - 1, memtable);
            final int rightRes = i + getMoneyAmount(i + 1, right, memtable);
            min = Math.min(min, Math.max(leftRes, rightRes));
        }
        memtable[left][right] = min;
        return min;
    }
}