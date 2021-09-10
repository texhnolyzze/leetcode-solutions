class Solution {
    public int search(
        final int[] nums,
        final int target
    ) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            final int mid = l + (r - l) / 2;
            final int midval = nums[mid];
            if (midval == target) {
                return mid;
            } else if (midval < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }
}