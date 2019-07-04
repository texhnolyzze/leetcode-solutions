class Solution {
    public static int thirdMax(int[] nums) {
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        long max        = Long.MIN_VALUE, 
            secondMax   = Long.MIN_VALUE, 
            thirdMax    = Long.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                thirdMax = secondMax;
                secondMax = max;
                max = nums[i];
            } else if (nums[i] > secondMax && nums[i] < max) {
                thirdMax = secondMax;
                secondMax = nums[i];
            } else if (nums[i] > thirdMax && nums[i] < max && nums[i] < secondMax)
                thirdMax = nums[i];
        }
        return (int) (thirdMax == Long.MIN_VALUE ? max : thirdMax);
    }
}