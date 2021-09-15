class Solution {
    public List<List<Integer>> combine(
        final int n,
        final int k
    ) {
        final List<List<Integer>> res = new LinkedList<>();
        combine(n, k, 0, 0, res, null);
        return res;
    }

    private void combine(
        final int n,
        final int k,
        final int used,
        final int from,
        final List<List<Integer>> res,
        final List<Integer> combination
    ) {
        if (Integer.bitCount(used) == k) {
            res.add(combination);
        } else {
            for (int i = from; i < n; i++) {
                if ((used & 1 << i) == 0) {
                    final List<Integer> spawn = new ArrayList<>(k);
                    if (combination != null) {
                        spawn.addAll(combination);
                    }
                    spawn.add(i + 1);
                    combine(n, k, used | 1 << i, i + 1, res, spawn);
                }
            }
        }
    }
}