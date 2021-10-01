class Solution {
    public int findMiddleIndex(final int[] nums) {
        final int n = nums.length;
        final int[] prefixSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        for (int i = 0; i < n; i++) {
            final int leftSum = prefixSum[i];
            final int rightSum = prefixSum[n] - prefixSum[i + 1];
            if (leftSum == rightSum) {
                return i;
            }
        }
        return -1;
    }
}