class Solution {
    public int minOperations(int[] nums1, int[] nums2) {
        int nums1MinSum = nums1.length;
        int nums1MaxSum = nums1.length * 6;
        int nums2MinSum = nums2.length;
        int nums2MaxSum = nums2.length * 6;
        if (!overlaps(nums1MinSum, nums1MaxSum, nums2MinSum, nums2MaxSum))
            return -1;
        int[] nums1Counts = new int[6];
        int[] nums2Counts = new int[6];
        int sum1 = 0;
        for (int i = 0; i < nums1.length; i++) {
            nums1Counts[nums1[i] - 1]++;
            sum1 += nums1[i];
        }
        int sum2 = 0;
        for (int i = 0; i < nums2.length; i++) {
            nums2Counts[nums2[i] - 1]++;
            sum2 += nums2[i];
        }
        int ops = 0;
        outer: while (sum1 != sum2) {
            int currDiff = Math.abs(sum1 - sum2);
            if (currDiff < 6) {
                for (int i = 0; i < nums1Counts.length; i++) {
                    if (nums1Counts[i] != 0) {
                        int num = i + 1;
                        if ((sum1 < sum2 && num + currDiff <= 6) || (sum1 > sum2 && num - currDiff > 0)) {
                            ops++;
                            break outer;
                        }
                    }
                }
                for (int i = 0; i < nums2Counts.length; i++) {
                    if (nums2Counts[i] != 0) {
                        int num = i + 1;
                        if ((sum1 < sum2 && num - currDiff > 0) || (sum1 > sum2 && num + currDiff <= 6)) {
                            ops++;
                            break outer;
                        }
                    }
                }
            }
            if (sum1 < sum2) {
                int minNum1Idx = 0;
                for (int i = 0; i < 6; i++) {
                    if (nums1Counts[i] != 0) {
                        minNum1Idx = i;
                        break;
                    }
                }
                int maxNum2Idx = 0;
                for (int i = 5; i >= 0; i--) {
                    if (nums2Counts[i] != 0) {
                        maxNum2Idx = i;
                        break;
                    }
                }
                int maxCanAddToNum1 = 6 - (minNum1Idx + 1);
                int maxCanSubtractFromNum2 = (maxNum2Idx + 1) - 1;
                if (maxCanAddToNum1 > maxCanSubtractFromNum2) {
                    sum1 += maxCanAddToNum1;
                    nums1Counts[minNum1Idx]--;
                    nums1Counts[minNum1Idx + maxCanAddToNum1]++;
                } else {
                    sum2 -= maxCanSubtractFromNum2;
                    nums2Counts[maxNum2Idx]--;
                    nums2Counts[maxNum2Idx - maxCanSubtractFromNum2]++;
                }
                ops++;
            } else {
                int maxNum1Idx = 0;
                for (int i = 5; i >= 0; i--) {
                    if (nums1Counts[i] != 0) {
                        maxNum1Idx = i;
                        break;
                    }
                }
                int minNum2Idx = 0;
                for (int i = 0; i < 6; i++) {
                    if (nums2Counts[i] != 0) {
                        minNum2Idx = i;
                        break;
                    }
                }
                int maxCanSubtractFromNum1 = (maxNum1Idx + 1) - 1;
                int maxCanAddToNum2 = 6 - (minNum2Idx + 1);
                if (maxCanSubtractFromNum1 > maxCanAddToNum2) {
                    sum1 -= maxCanSubtractFromNum1;
                    nums1Counts[maxNum1Idx]--;
                    nums1Counts[maxNum1Idx - maxCanSubtractFromNum1]++;
                } else {
                    sum2 += maxCanAddToNum2;
                    nums2Counts[minNum2Idx]--;
                    nums2Counts[minNum2Idx + maxCanAddToNum2]++;
                }
                ops++;
            }
        }
        return ops;
    }

    private boolean overlaps(
        final int a,
        final int b,
        final int c,
        final int d
    ) {
        return Math.max(a, c) <= Math.min(b, d);
    }
}