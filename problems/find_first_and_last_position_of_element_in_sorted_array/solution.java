class Solution {
    public static int[] searchRange(int[] nums, int target) {
        int left = searchLeft(nums, target);
        if (left == -1)
            return new int[] {-1, -1};
        int right = searchRight(nums, target);
        return new int[] {left, right};
    }

    private static int searchLeft(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] > target) 
                r = mid - 1;
            else if (nums[mid] < target) 
                l = mid + 1;
            else {
                if (mid - 1 < 0 || nums[mid - 1] < target)
                    return mid;
                else 
                    r = mid - 1;
            }
        }
        return -1;
    }

    private static int searchRight(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] > target) 
                r = mid - 1;
            else if (nums[mid] < target) 
                l = mid + 1;
            else {
                if (mid + 1 > nums.length - 1 || nums[mid + 1] > target)
                    return mid;
                else 
                    l = mid + 1;
            }
        }
        return -1;
    }
}