class Solution {
    public int findMin(int[] nums) {
        return findMin(nums, 0, nums.length - 1);
    }

    private int findMin(
        final int[] nums,
        final int left,
        final int right
    ) {
        if (left > right)
            return Integer.MAX_VALUE;
        int mid = left + (right - left) / 2;
        int num = nums[mid];
        int leftMin;
        int rightMin;
        if (num >= nums[left]) {
            leftMin = nums[left];
            rightMin = findMin(nums, mid + 1, right);
        } else {
            rightMin = nums[mid];
            leftMin = findMin(nums, left, mid - 1);
        }
        return Math.min(leftMin, rightMin);
    }
}