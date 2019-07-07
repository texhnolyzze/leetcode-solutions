class Solution {
    public int pivotIndex(int[] nums) {
        if (nums.length < 3)
            return -1;
        int rightSum = 0;
        for (int i = 1; i < nums.length; i++)
            rightSum += nums[i];
        int leftSum = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (leftSum == rightSum)
                return i;
            leftSum += nums[i];
            rightSum -= nums[i + 1];
        }
        if (leftSum == 0)
            return nums.length - 1;
        return -1;
    }
}