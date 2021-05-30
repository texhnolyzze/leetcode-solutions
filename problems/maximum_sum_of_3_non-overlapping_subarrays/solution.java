class Solution {
    public int[] maxSumOfThreeSubarrays(
        final int[] nums,
        final int k
    ) {
        final int[] windows = calcWindows(nums, k);
        final int[] maxThreeWindows = {-1, -1, -1};
        int maxThreeWindowsSum = -1;
        for (int midWindowIdx = k; midWindowIdx < windows.length - k; midWindowIdx++) {
            int maxLeftWindowIdx = 0;
            int maxRightWindowIdx = midWindowIdx + k;
            for (int leftWindowIdx = 1; leftWindowIdx + k - 1 < midWindowIdx; leftWindowIdx++) {
                if (windows[maxLeftWindowIdx] < windows[leftWindowIdx]) {
                    maxLeftWindowIdx = leftWindowIdx;
                }
            }
            for (int rightWindowIdx = maxRightWindowIdx + 1; rightWindowIdx < windows.length; rightWindowIdx++) {
                if (windows[maxRightWindowIdx] < windows[rightWindowIdx]) {
                    maxRightWindowIdx = rightWindowIdx;
                }
            }
            final int candidateWindowsSum = windows[maxLeftWindowIdx] + windows[midWindowIdx] + windows[maxRightWindowIdx];
            if (maxThreeWindowsSum == -1 || maxThreeWindowsSum < candidateWindowsSum) {
                maxThreeWindowsSum = candidateWindowsSum;
                maxThreeWindows[0] = maxLeftWindowIdx;
                maxThreeWindows[1] = midWindowIdx;
                maxThreeWindows[2] = maxRightWindowIdx;
            }
        }
        return maxThreeWindows;
    }

    private int[] calcWindows(final int[] nums, final int k) {
        final int[] windows = new int[nums.length - k + 1];
        int sum = 0;
        for (int i = 0, windowIdx = 0, windowSize = 0; i < nums.length; i++) {
            if (windowSize < k) {
                sum += nums[i];
                windowSize++;
                if (windowSize == k) {
                    windows[windowIdx++] = sum;
                }
            } else {
                sum = sum + nums[i] - nums[i - k];
                windows[windowIdx++] = sum;
            }
        }
        return windows;
    }
}