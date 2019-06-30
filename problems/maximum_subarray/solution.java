class Solution {
    public int maxSubArray(int[] nums) {
        return maxSubArray(nums, 0, nums.length - 1);
    }
    
    private static int maxSubArray(int[] nums, int left, int right) {
        if (left >= right)
            return nums[left];
        int mid = left + (right - left) / 2;
        int leftArrMaxSubArray = maxSubArray(nums, left, mid - 1);
        int rightArrMaxSubArray = maxSubArray(nums, mid + 1, right);
        int maxSubArrayCrossingMiddle = maxCrossingMiddleSubArray(nums, left, mid, right);
        return Math.max(leftArrMaxSubArray, Math.max(rightArrMaxSubArray, maxSubArrayCrossingMiddle));
    }
    
    private static int maxCrossingMiddleSubArray(int[] nums, int left, int mid, int right) {
        int leftSum = 0, rightSum = 0, leftSumMax = 0, rightSumMax = 0;
        for (int i = mid - 1; i >= left; i--) {
            leftSum += nums[i];
            if (leftSum > leftSumMax) 
                leftSumMax = leftSum;
        }
        for (int i = mid + 1; i <= right; i++) {
            rightSum += nums[i];
            if (rightSum > rightSumMax) 
                rightSumMax = rightSum;
        }
        return leftSumMax + rightSumMax + nums[mid];
    }
}