class Solution {
    public List<List<Integer>> combinationSum2(
        final int[] candidates,
        final int target
    ) {
        Arrays.sort(candidates);
        final List<List<Integer>> res = new LinkedList<>();
        backtrack(
            candidates,
            0,
            target,
            Collections.emptyList(),
            res
        );
        return res;
    }

    private void backtrack(
        final int[] candidates,
        final int from,
        final int target,
        final List<Integer> current,
        final List<List<Integer>> res
    ) {
        if (target < 0) {
            return;
        } else if (target == 0) {
            res.add(current);
        } else {
            if (from >= candidates.length) {
                return;
            }
            final int fromVal = candidates[from];
            if (target < fromVal) {
                return;
            }
            int nextNotEqualToFrom = from + 1;
            while (nextNotEqualToFrom < candidates.length && candidates[nextNotEqualToFrom] == fromVal) {
                nextNotEqualToFrom++;
            }
            final List<Integer> spawnNotIncludingFrom = current.isEmpty() ? current : new ArrayList<>(current);
            backtrack(candidates, nextNotEqualToFrom, target, spawnNotIncludingFrom, res);
            int i = from;
            do {
                final List<Integer> spawnIncludingFrom = new ArrayList<>(current);
                for (int j = i; j >= from; j--) {
                    spawnIncludingFrom.add(fromVal);
                }
                backtrack(candidates, nextNotEqualToFrom, target - fromVal * (i - from + 1), spawnIncludingFrom, res);
                i++;
            } while (i < candidates.length && candidates[i] == fromVal);

        }
    }
}