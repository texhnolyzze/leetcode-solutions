class Solution {
    public int numOfMinutes(
        final int n,
        final int headId,
        final int[] manager,
        final int[] informTime
    ) {
        final Map<Integer, List<Integer>> subordinates = new HashMap<>();
        for (int empId = 0; empId < n; empId++) {
            if (empId == headId) {
                continue;
            }
            final int supervisor = manager[empId];
            subordinates.computeIfAbsent(
                supervisor,
                unused -> new LinkedList<>()
            ).add(empId);
        }
        return informTime(headId, subordinates, informTime);
    }

    private int informTime(
        final int rootId,
        final Map<Integer, List<Integer>> subordinates,
        final int[] informTime
    ) {
        final List<Integer> emps = subordinates.get(rootId);
        if (emps == null || emps.isEmpty()) {
            return 0;
        }
        int downstreamMax = 0;
        for (final int empId : emps) {
            downstreamMax = Math.max(
                downstreamMax,
                informTime(
                    empId,
                    subordinates,
                    informTime
                )
            );
        }
        return informTime[rootId] + downstreamMax;
    }
}