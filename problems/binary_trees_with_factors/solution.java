class Solution {
    private static final int MOD = (int) (1E9 + 7);

    public int numFactoredBinaryTrees(final int[] arr) {
        Arrays.sort(arr);
        final long[] memtable = new long[arr.length];
        long res = 0;
        for (int i = 0; i < arr.length; i++) {
            res = res + numFactoredBinaryTrees(arr, i, memtable);
        }
        return (int) (res % MOD);
    }

    private long numFactoredBinaryTrees(
        final int[] arr,
        final int rootIdx,
        final long[] memtable
    ) {
        long res = memtable[rootIdx];
        if (res != 0) {
            return res;
        }
        res = 1;
        final int root = arr[rootIdx];
        for (int leftIdx = rootIdx - 1; leftIdx >= 0; leftIdx--) {
            final int leftVal = arr[leftIdx];
            if (root % leftVal == 0) {
                final int rightIdx = Arrays.binarySearch(arr, root / leftVal);
                if (rightIdx >= 0) {
                    final long subLeft = numFactoredBinaryTrees(arr, leftIdx, memtable);
                    final long subRight = numFactoredBinaryTrees(arr, rightIdx, memtable);
                    res = (res + subLeft * subRight) % MOD;
                }
            }
        }
        memtable[rootIdx] = res;
        return res;
    }
}