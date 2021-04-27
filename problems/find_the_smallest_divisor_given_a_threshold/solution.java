public class Solution {

    public int smallestDivisor(int[] nums, int threshold) {
        int left = 1;
        int right = IntStream.of(nums).max().getAsInt();
        int ans = -1;
        while (left <= right) {
            ans = left + (right - left) / 2;
            int targetSum = sum(nums, ans);
            if (targetSum > threshold)
                left = ans + 1;
            else {
                if (ans == 1)
                    break;
                int maybeGreaterSum = sum(nums, ans - 1);
                if (maybeGreaterSum > threshold)
                    break;
                else
                    right = ans - 1;
            }
        }
        return ans;
    }

    private int sum(final int[] nums, final int divisor) {
        int res = 0;
        for (int num : nums) {
            res += num % divisor == 0 ? num / divisor : num / divisor + 1;
        }
        return res;
    }

}