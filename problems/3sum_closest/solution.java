import java.util.*;

class Solution {
    public int threeSumClosest(
        final int[] nums,
        final int target
    ) {
        Arrays.sort(nums);
        int minDiff = Integer.MAX_VALUE;
        int minDiffSum = -1;
        for (int i = 0; i < nums.length - 1 && minDiff != 0; i++) {
            final int num = nums[i];
            int lo = i + 1;
            int hi = nums.length - 1;
            while (lo < hi) {
                final int sum = num + nums[lo] + nums[hi];
                final int diff = Math.abs(target - sum);
                if (diff < minDiff) {
                    minDiff = diff;
                    minDiffSum = sum;
                }
                if (sum < target) {
                    lo++;
                } else {
                    hi--;
                }
            }
        }
        return minDiffSum;
    }
}