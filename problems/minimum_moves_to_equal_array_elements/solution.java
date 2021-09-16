class Solution {
    public int minMoves(final int[] nums) {
        final int sum = Arrays.stream(nums).sum();
        final int min = Arrays.stream(nums).min().orElseThrow();
        final int n = nums.length;
        return sum - min * n;
    }
}