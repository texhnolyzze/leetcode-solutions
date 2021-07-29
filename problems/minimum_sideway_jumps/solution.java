class Solution {
    private final int unknown = -1;
    private final int noSolution = -2;

    public int minSideJumps(final int[] obstacles) {
        final int[][][] minJumps = new int[3][2][obstacles.length];
        for (int i = 0; i < minJumps.length; i++) {
            Arrays.fill(minJumps[i][0], unknown);
            Arrays.fill(minJumps[i][1], unknown);
        }
        return minJumps(
            obstacles,
            0,
            1,
            minJumps,
            false
        );
    }

    private int minJumps(
        final int[] obstacles,
        final int n,
        final int lane,
        final int[][][] minJumps,
        final boolean crossed
    ) {
        if (n == obstacles.length - 1) {
            return 0;
        }
        int res = minJumps[lane][crossed ? 1 : 0][n];
        if (res != unknown) {
            return res;
        }
        res = Integer.MAX_VALUE;
        final int nextObstacle = obstacles[n + 1];
        if (nextObstacle == 0 || nextObstacle != lane + 1) {
            res = minJumps(obstacles, n + 1, lane, minJumps, false);
        }
        if (!crossed) {
            final int currObstacle = obstacles[n];
            for (int laneIdx = 0; laneIdx < 3; laneIdx++) {
                if (
                    (currObstacle == 0 || currObstacle != laneIdx + 1)
                ) {
                    final int sub = minJumps(obstacles, n, laneIdx, minJumps, true);
                    if (sub != noSolution) {
                        res = Math.min(res, 1 + sub);
                    }
                }
            }
        }
        if (res == Integer.MAX_VALUE) {
            res = noSolution;
        }
        minJumps[lane][crossed ? 1 : 0][n] = res;
        return res;
    }
}