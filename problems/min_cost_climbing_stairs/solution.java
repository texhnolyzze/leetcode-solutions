class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int[] memtable = new int[cost.length + 1];
        Arrays.fill(memtable, -1);
        return solve(cost, cost.length, memtable);
    }

    private int solve(final int[] cost, final int stairIndex, final int[] memtable) {
        int res = memtable[stairIndex];
        if (res != -1)
            return res;
        if (stairIndex == 0)
            res = cost[0];
        else if (stairIndex == 1)
            res = cost[1];
        else {
            int currCost = stairIndex == cost.length ? 0 : cost[stairIndex];
            int candidate1 = solve(cost, stairIndex - 1, memtable);
            int candidate2 = solve(cost, stairIndex - 2, memtable);
            res = Math.min(candidate1, candidate2) + currCost;
        }
        memtable[stairIndex] = res;
        return res;
    }
}