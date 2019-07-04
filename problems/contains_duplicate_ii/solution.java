class Solution {
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums.length < 2)
            return false;
        if (k >= nums.length)
            k = nums.length - 1;
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i <= k; i++) {
            if (m.merge(nums[i], 1, Integer::sum) > 1)
                return true;
        }
        for (int i = k + 1; i < nums.length; i++) {
            m.merge(nums[i - (k + 1)], 1, (prevCount, one) -> {
                Integer res = prevCount - one;
                return res == 0 ? null : res;
            });
            if (m.merge(nums[i], 1, Integer::sum) > 1)
                return true;
        }
        return false;
    }
}