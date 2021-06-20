class Solution {
    public int numTrees(int n) {
        final int[][] memtable = new int[n + 1][n + 1];
        return numTrees(1, n, memtable);
    }

    private int numTrees(final int left, final int right, final int[][] memtable) {
        if (left > right) {
            return 0;
        } else if (left == right) {
            return 1;
        } else {
            int res = memtable[left][right];
            if (res != 0)
                return res;
            res = numTrees(left + 1, right, memtable) + numTrees(left, right - 1, memtable);
            for (int root = left + 1; root <= right - 1; root++) {
                final int numLeftTrees = numTrees(left, root - 1, memtable);
                final int numRightTrees = numTrees(root + 1, right, memtable);
                res += (numLeftTrees * numRightTrees);
            }
            memtable[left][right] = res;
            return res;
        }
    }
}