class Solution {
    public int minSubArrayLen(
        final int target,
        final int[] nums
    ) {
        int l = 0;
        int r = 0;
        int sum = nums[0];
        int res = Integer.MAX_VALUE;
        while (l < nums.length || r < nums.length) {
            if (sum < target) {
                if (r != nums.length - 1) {
                    sum += nums[++r];
                } else {
                    break;
                }
            } else if (sum >= target) {
                res = Math.min(
                    res,
                    r - l + 1
                );
                sum -= nums[l++];
                if (l > r) {
                    r = l;
                }
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}