class Solution {
    public int findMaxConsecutiveOnes(final int[] nums) {
        int max = 0;
        int count = 0;
        for (final int num : nums) {
            if (num == 1) {
                count++;
            } else {
                max = Math.max(max, count);
                count = 0;
            }
        }
        max = Math.max(max, count);
        return max;
    }
}