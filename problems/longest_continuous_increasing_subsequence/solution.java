class Solution {
    public int findLengthOfLCIS(int[] nums) {
        if (nums.length == 0)
            return 0;
        int longest = 1;
        int left = 0, right = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] <= nums[i - 1]) {
                longest = Math.max(longest, right - left + 1);
                left = i;
            } else 
                right = i;
        }
        return Math.max(longest, right - left + 1);
    }
}