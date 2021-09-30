class Solution {
    public boolean kLengthApart(
        final int[] nums,
        final int k
    ) {
        final int n = nums.length;
        int left = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                if (
                    left != -1 && 
                    i - left - 1 < k
                ) {
                    return false;
                }
                left = i;
            }
        }
        return true;
    }
}