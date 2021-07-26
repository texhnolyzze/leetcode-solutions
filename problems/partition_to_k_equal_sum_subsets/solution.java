class Solution {
    private Map<Integer, Set<Integer>>[] sumsToTarget;

    public boolean canPartitionKSubsets(
        final int[] nums,
        final int k
    ) {
        final int sum = Arrays.stream(nums).sum();
        final int targetSum = sum / k;
        if (targetSum * k != sum) {
            return false;
        }
        sumsToTarget = new HashMap[1 << nums.length];
        return canPartitionKSubsets(nums, k - 1, 0, targetSum);
    }

    private boolean canPartitionKSubsets(
        final int[] nums,
        final int partitionIdx,
        final int used,
        final int targetSum
    ) {
        if (partitionIdx == -1) {
            return true;
        } else {
            final Set<Integer> sums = sumsToTarget(targetSum, used, nums);
            if (sums.isEmpty()) {
                return false;
            }
            for (final Integer subUsed : sums) {
                if (canPartitionKSubsets(nums, partitionIdx - 1, subUsed, targetSum)) {
                    return true;
                }
            }
        }
        return false;
    }

    private Set<Integer> sumsToTarget(
        final int targetSum,
        final int used,
        final int[] nums
    ) {
        Map<Integer, Set<Integer>> memtable = sumsToTarget[used];
        if (memtable == null) {
            memtable = new HashMap<>();
            sumsToTarget[used] = memtable;
        }
        Set<Integer> res = memtable.get(targetSum);
        if (res != null) {
            return res;
        }
        res = new HashSet<>(1);
        for (int i = 0; i < nums.length; i++) {
            if ((used & (1 << i)) != 0) {
                continue;
            }
            final int num = nums[i];
            final int sub = targetSum - num;
            if (sub > 0) {
                res.addAll(
                    sumsToTarget(
                        sub,
                        used | (1 << i),
                        nums
                    )
                );
            } else if (sub == 0) {
                res.add(used | (1 << i));
            }
        }
        memtable.put(targetSum, res);
        return res;
    }
}