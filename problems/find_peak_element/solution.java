class Solution {
    public static int findPeakElement(int[] nums) {
        if (nums.length == 1)
            return 0;
        int l = nums[0];
        int r = nums[nums.length - 1];
        if (l > nums[1]) 
            return 0;
        if (r > nums[nums.length - 2])
            return nums.length - 1;
        return peak(nums, 0, nums.length - 1);
    }

    private static int peak(int[] nums, int left, int right) {
        if (left > right)
            return -1;
        if (right - left < 2)
            return -1;
        int mid = left + (right - left) / 2;
        if (nums[mid - 1] < nums[mid] && nums[mid + 1] < nums[mid])
            return mid;
        int lPeak = peak(nums, left, mid);
        int rPeak = peak(nums, mid, right);
        return lPeak == -1 ? rPeak : lPeak;
    }
}