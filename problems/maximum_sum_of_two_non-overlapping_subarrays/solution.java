class Solution {
    public int maxSumTwoNoOverlap(
        final int[] nums,
        final int firstLen,
        final int secondLen
    ) {
        final int[] prefixSum = new int[nums.length];
        prefixSum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i <= nums.length - firstLen; i++) {
            final int firstSum = prefixSum[i + firstLen - 1] - (i == 0 ? 0 : prefixSum[i - 1]);
            for (int j = i + firstLen; j <= nums.length - secondLen; j++) {
                final int secondSum = prefixSum[j + secondLen - 1] - prefixSum[j - 1];
                max = Math.max(
                    max,
                    firstSum + secondSum
                );
            }
            for (int j = 0; j + secondLen - 1 < i; j++) {
                final int secondSum = prefixSum[j + secondLen - 1] - (j == 0 ? 0 : prefixSum[j - 1]);
                max = Math.max(
                    max,
                    firstSum + secondSum
                );
            }
        }
        return max;
    }
}