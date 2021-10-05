class Solution {
    public int findKthLargest(
        final int[] nums,
        final int k
    ) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
}