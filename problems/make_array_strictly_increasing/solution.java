class Solution {
    private static final int NO_SOLUTION = -1;
    private static final int SOLUTION_UNKNOWN = -2;

    private static final int NO_SWAP = 0;

    public int makeArrayIncreasing(final int[] arr1, int[] arr2) {
        arr2 = Arrays.stream(arr2).distinct().toArray();
        Arrays.sort(arr2);
        final int n = arr1.length;
        final int[][] memtable = new int[n][arr2.length + 1];
        for (int i = 0; i < memtable.length; i++) {
            Arrays.fill(memtable[i], SOLUTION_UNKNOWN);
        }
        int res = makeArrayIncreasing(arr1, arr2, n - 1, NO_SWAP, memtable);
        for (int i = 1; i <= arr2.length; i++) {
            final int temp = makeArrayIncreasing(arr1, arr2, n - 1, i, memtable);
            if (temp != NO_SOLUTION) {
                if (res == NO_SOLUTION) {
                    res = temp;
                } else {
                    res = Math.min(res, temp);
                }
            }
        }
        return res;
    }

    private int makeArrayIncreasing(
        final int[] arr1,
        final int[] arr2,
        final int lastIdx,
        final int lastValueIdx,
        final int[][] memtable
    ) {
        if (lastIdx == 0) {
            return lastValueIdx == NO_SWAP ? 0 : 1;
        } else {
            final int currNum = lastValueIdx == NO_SWAP ? arr1[lastIdx] : arr2[lastValueIdx - 1];
            int res = memtable[lastIdx][lastValueIdx];
            if (res != SOLUTION_UNKNOWN)
                return res;
            res = NO_SOLUTION;
            if (currNum > arr1[lastIdx - 1]) {
                final int temp = makeArrayIncreasing(arr1, arr2, lastIdx - 1, NO_SWAP, memtable);
                if (temp != NO_SOLUTION) {
                    res = (lastValueIdx == NO_SWAP ? 0 : 1) + temp;
                }
            }
            final int bs = Arrays.binarySearch(arr2, currNum);
            final int idx = bs < 0 ? -bs - 2 : bs - 1;
            if (idx >= 0) {
                final int temp = makeArrayIncreasing(arr1, arr2, lastIdx - 1, idx + 1, memtable);
                if (temp != NO_SOLUTION) {
                    if (res == NO_SOLUTION) {
                        res = (lastValueIdx == NO_SWAP ? 0 : 1) + temp;
                    } else {
                        res = Math.min(res, (lastValueIdx == NO_SWAP ? 0 : 1) + temp);
                    }
                }
            }
            memtable[lastIdx][lastValueIdx] = res;
            return res;
        }
    }
}