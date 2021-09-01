class Solution {
    public int findTargetSumWays(
        final int[] nums,
        final int target
    ) {
        final int sum = Arrays.stream(nums).sum();
        final int[][] memtable = new int[nums.length][sum * 2 + 1];
        for (int i = 0; i < memtable.length; i++) {
            Arrays.fill(memtable[i], -1);
        }
        return numWays(nums, sum, target, 0, 0, memtable);
    }

    private int numWays(
        final int[] nums,
        final int sum,
        final int target,
        final int idx,
        final int sumSoFar,
        final int[][] memtable
    ) {
        if (idx == nums.length) {
            return sumSoFar == target ? 1 : 0;
        }
        int res = memtable[idx][sumSoFar + sum];
        if (res != -1) {
            return res;
        }
        res = numWays(
            nums,
            sum,
            target,
            idx + 1,
            sumSoFar + nums[idx],
            memtable
        ) + numWays(
            nums,
            sum,
            target,
            idx + 1,
            sumSoFar - nums[idx],
            memtable
        );
        memtable[idx][sumSoFar + sum] = res;
        return res;
    }
}