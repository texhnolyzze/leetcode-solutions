import java.util.NavigableMap;
import java.util.TreeMap;

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0)
            return new int[] {};
        int[] ans = new int[nums.length - k + 1];
        NavigableMap<Integer, Integer> m = new TreeMap<>();
        for (int i = 0; i < k; i++)
            m.merge(nums[i], 1, Integer::sum);
        ans[0] = m.lastKey();
        for (int i = 1; i <= nums.length - k; i++) {
            m.compute(nums[i - 1], (num, count) -> {
                if (count < 2)
                    return null;
                return count - 1;
            });
            m.merge(nums[i + k - 1], 1, Integer::sum);
            ans[i] = m.lastKey();
        }
        return ans;
    }
}