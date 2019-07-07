class Solution {
    public int[] findErrorNums(int[] nums) {
        int expectedSum = nums.length * (nums.length + 1) / 2;
        int[] countOf = new int[nums.length];
        int sum = 0;
        int duplicate = -1;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            countOf[nums[i] - 1]++;
            if (countOf[nums[i] - 1] > 1)
                duplicate = nums[i];
        }
        return new int[] {duplicate, duplicate + (expectedSum - sum)};
    }
}