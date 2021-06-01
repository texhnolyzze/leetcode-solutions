class Solution {
    public List<Integer> minSubsequence(final int[] nums) {
        Arrays.sort(nums);
        final int total = Arrays.stream(nums).sum();
        final List<Integer> res = new ArrayList<>();
        int sum = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            final int num = nums[i];
            sum += num;
            res.add(num);
            if (sum > total - sum)
                break;
        }
        return res;
    }
}