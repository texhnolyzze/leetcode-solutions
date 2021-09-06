class Solution {
    public int maxSizeSlices(final int[] slices) {
        final int[] s1 = Arrays.copyOfRange(slices, 0, slices.length - 1);
        final int[] s2 = Arrays.copyOfRange(slices, 1, slices.length);
        return Math.max(
            maxSizeSlices(s1, slices.length / 3),
            maxSizeSlices(s2, slices.length / 3)
        );
    }

    private int maxSizeSlices(
        final int[] slices,
        final int n
    ) {
        final int[][] memtable = new int[slices.length + 2][n + 1];
        for (int i = 0; i < memtable.length; i++) {
            Arrays.fill(memtable[i], -1);
        }
        return maxSizeSlices(
            slices,
            slices.length - 1,
            n,
            memtable
        );
    }

    private int maxSizeSlices(
        final int[] slices,
        final int end,
        final int slicesLeft,
        final int[][] memtable
    ) {
        if (slicesLeft == 0 || end < 0) {
            return 0;
        }
        int res = memtable[end][slicesLeft];
        if (res != -1) {
            return res;
        }
        final int ifNotPick = maxSizeSlices(slices, end - 1, slicesLeft, memtable);
        final int ifPick = slices[end] + maxSizeSlices(slices, end - 2, slicesLeft - 1, memtable);
        res = Math.max(
            ifPick,
            ifNotPick
        );
        memtable[end][slicesLeft] = res;
        return res;
    }
}