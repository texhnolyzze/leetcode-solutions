class Solution {
    public int maxCoins(int[] nums) {
        final int max = Arrays.stream(nums).max().orElseThrow();
        final int[][][] memtable = new int[nums.length][nums.length][Math.max(2, max + 1)];
        for (int i = 0; i < memtable.length; i++) {
            for (int j = 0; j < memtable[i].length; j++) {
                Arrays.fill(memtable[i][j], -1);
            }
        }
        return maxCoins(nums, 0, nums.length - 1, 1, memtable);
    }

    private int maxCoins(
        final int[] balloons,
        final int from,
        final int to,
        final int leftBalloon,
        final int[][][] memtable
    ) {
        if (from > to) {
            return 0;
        } else {
            final int balloon = balloons[from];
            if (from == to) {
                return leftBalloon * balloon * (to + 1 < balloons.length ? balloons[to + 1] : 1);
            } else {
                int res = memtable[from][to][leftBalloon];
                if (res != -1)
                    return res;
                res = leftBalloon * balloon * balloons[from + 1] + maxCoins(balloons, from + 1, to, leftBalloon, memtable);
                for (int i = from + 1; i <= to; i++) {
                    res = Math.max(
                        res,
                        maxCoins(balloons, from + 1, i, balloon, memtable) + maxCoins(balloons, i + 1, to, leftBalloon, memtable) + leftBalloon * balloon * ((i + 1 < balloons.length) ? balloons[i + 1] : 1)
                    );
                }
                memtable[from][to][leftBalloon] = res;
                return res;
            }
        }
    }
}