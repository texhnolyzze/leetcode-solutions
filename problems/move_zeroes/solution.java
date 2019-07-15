class Solution {
    public void moveZeroes(int[] nums) {
        int numZeros = 0;
        int writeToIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[writeToIndex++] = nums[i];
            } else
                numZeros++;
        }
        for (int i = nums.length - 1; i >= nums.length - numZeros; i--) {
            nums[i] = 0;
        }
    }
}