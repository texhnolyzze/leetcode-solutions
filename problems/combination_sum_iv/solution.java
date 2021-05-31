class Solution {
    public int combinationSum4(final int[] nums, final int target) {
        final int[] memtable = new int[target + 1];
        Arrays.fill(memtable, -1);
        return combinationSum(nums, target, memtable);
    }

    private int combinationSum(
        final int[] nums,
        final int target,
        final int[] memtable
    ) {
        if (target < 0)
            return 0;
        else if (target == 0)
            return 1;
        int res = memtable[target];
        if (res != -1)
            return res;
        res = 0;
        for (final int num : nums) {
            res += combinationSum(nums, target - num, memtable);
        }
        memtable[target] = res;
        return res;
    }
}