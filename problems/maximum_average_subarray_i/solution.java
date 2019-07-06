class Solution {
    public static double findMaxAverage(int[] nums, int k) {
        double max = 0;
        double kInv = 1.0 / k;
        for (int i = 0; i < k; i++) 
            max += nums[i];
        max *= kInv;
        double curr = max;
        for (int i = k; i < nums.length; i++) {
            curr = curr - kInv * (nums[i - k] - nums[i]);
            if (curr > max)
                max = curr;
        }
        return max;
    }
}