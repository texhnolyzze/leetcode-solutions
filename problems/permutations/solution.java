class Solution {
    public List<List<Integer>> permute(final int[] nums) {
        int numPermutations = 1;
        for (int i = 2; i <= nums.length; i++) {
            numPermutations *= i;
        }
        final List<List<Integer>> res = new ArrayList<>(numPermutations);
        permute(nums, 0, res, null);
        return res;
    }

    private void permute(
        final int[] nums,
        final int used,
        final List<List<Integer>> res,
        final List<Integer> permutation
    ) {
        final int mask = (1 << nums.length) - 1;
        if (used == mask) {
            res.add(permutation);
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if ((used & (1 << i)) != 0) {
                continue;
            }
            final List<Integer> spawn = new ArrayList<>(nums.length);
            if (permutation != null) {
                spawn.addAll(permutation);
            }
            spawn.add(nums[i]);
            permute(nums, used | 1 << i, res, spawn);
        }
    }
}