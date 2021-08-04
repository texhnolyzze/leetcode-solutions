class Solution {
    private final int unknown = -1;
    private final int noSolution = -2;

    private final int a = 0;
    private final int b = 1;

    public int twoCitySchedCost(final int[][] costs) {
        final int[][][] memtable = new int[costs.length][costs.length][costs.length];
        for (int i = 0; i < memtable.length; i++) {
            for (int j = 0; j < memtable[i].length; j++) {
                Arrays.fill(memtable[i][j], unknown);
            }
        }
        return twoCitySchedCost(costs, 0, memtable, 0, 0);
    }

    private int twoCitySchedCost(
        final int[][] costs,
        final int idx,
        final int[][][] memtable,
        final int numToA,
        final int numToB
    ) {
        if (idx >= costs.length) {
            return numToA == numToB && numToB == costs.length / 2 ? 0 : noSolution;
        }
        int res = memtable[idx][numToA][numToB];
        if (res != unknown) {
            return res;
        }
        final int ifScheduleToA = twoCitySchedCost(costs, idx + 1, memtable, numToA + 1, numToB);
        final int ifScheduleToB = twoCitySchedCost(costs, idx + 1, memtable, numToA, numToB + 1);
        if (ifScheduleToA != noSolution) {
            res = costs[idx][a] + ifScheduleToA;
            if (ifScheduleToB != noSolution) {
                res = Math.min(res, costs[idx][b] + ifScheduleToB);
            }
        } else {
            if (ifScheduleToB != noSolution) {
                res = costs[idx][b] + ifScheduleToB;
            } else {
                res = noSolution;
            }
        }
        memtable[idx][numToA][numToB] = res;
        return res;
    }
}