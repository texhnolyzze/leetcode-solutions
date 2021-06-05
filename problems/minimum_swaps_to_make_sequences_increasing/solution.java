class Solution {
    private static final int INF = 1000000000;

    public int minSwap(
        final int[] nums1,
        final int[] nums2
    ) {
        final int n = nums1.length;
        final int[][] memtable = new int[n][2];
        for (int i = 0; i < memtable.length; i++) {
            Arrays.fill(memtable[i], -1);
        }
        final int min = Math.min(
            minSwap(nums1, nums2, n - 1, false, memtable),
            minSwap(nums1, nums2, n - 1, true, memtable)
        );
        return min;
    }

    private int minSwap(
        final int[] nums1,
        final int[] nums2,
        final int last,
        final boolean swapLast,
        final int[][] memtable
    ) {
        if (last == 0) {
            return swapLast ? 1 : 0;
        } else {
            int res = memtable[last][swapLast(swapLast)];
            if (res != -1)
                return res;
            final int num1 = swapLast ? nums2[last] : nums1[last];
            final int num2 = swapLast ? nums1[last] : nums2[last];
            final int prevNum1 = nums1[last - 1];
            final int prevNum2 = nums2[last - 1];
            if (num1 > prevNum1) {
                if (num1 > prevNum2) {
                    if (num2 > prevNum1) {
                        if (num2 > prevNum2) {
                            res = swapLast(swapLast) + Math.min(
                                minSwap(nums1, nums2, last - 1, true, memtable),
                                minSwap(nums1, nums2, last - 1, false, memtable)
                            );
                        } else {
                            res = swapLast(swapLast) + minSwap(nums1, nums2, last - 1, true, memtable);
                        }
                    } else {
                        if (num2 > prevNum2) {
                            res = swapLast(swapLast) + minSwap(nums1, nums2, last - 1, false, memtable);
                        } else {
                            res = INF;
                        }
                    }
                } else {
                    if (num2 > prevNum2) {
                        res = swapLast(swapLast) + minSwap(nums1, nums2, last - 1, false, memtable);
                    } else {
                        res = INF;
                    }
                }
            } else {
                if (num1 > prevNum2) {
                    if (num2 > prevNum1) {
                        res = swapLast(swapLast) + minSwap(nums1, nums2, last - 1, true, memtable);
                    } else {
                        res = INF;
                    }
                } else {
                    res = INF;
                }
            }
            memtable[last][swapLast(swapLast)] = res;
            return res;
        }
    }

    private int swapLast(boolean swap) {
        return swap ? 1 : 0;
    }
}