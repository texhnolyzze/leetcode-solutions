class Solution {
    public int rob(final int[] houses) {
        if (houses.length == 1) {
            return houses[0];
        }
        final int[][] memtable = new int[2][houses.length];
        Arrays.fill(memtable[0], -1);
        Arrays.fill(memtable[1], -1);
        final int s1 = robWithoutLast(houses, 0, 0, memtable);
        Arrays.fill(memtable[0], -1);
        Arrays.fill(memtable[1], -1);
        final int s2 = robWithoutFirst(houses, 1, 0, memtable);
        return Math.max(s1, s2);
    }

    private int robWithoutLast(
        final int[] houses,
        final int houseIdx,
        final int previousRobbed,
        final int[][] memtable
    ) {
        if (houseIdx == houses.length - 1) {
            return 0;
        }
        int res = memtable[previousRobbed][houseIdx];
        if (res != -1) {
            return res;
        }
        final int nextHouseIdx = houseIdx + 1;
        if (previousRobbed == 0) {
            res = Math.max(
                houses[houseIdx] + robWithoutLast(houses, nextHouseIdx, 1, memtable),
                robWithoutLast(houses, nextHouseIdx, 0, memtable)
            );
        } else {
            res = robWithoutLast(houses, nextHouseIdx, 0, memtable);
        }
        memtable[previousRobbed][houseIdx] = res;
        return res;
    }

    private int robWithoutFirst(
        final int[] houses,
        final int houseIdx,
        final int previousRobbed,
        final int[][] memtable
    ) {
        if (houseIdx >= houses.length) {
            return 0;
        }
        int res = memtable[previousRobbed][houseIdx];
        if (res != -1) {
            return res;
        }
        final int nextHouseIdx = houseIdx + 1;
        if (previousRobbed == 0) {
            res = Math.max(
                houses[houseIdx] + robWithoutFirst(houses, nextHouseIdx, 1, memtable),
                robWithoutFirst(houses, nextHouseIdx, 0, memtable)
            );
        } else {
            res = robWithoutFirst(houses, nextHouseIdx, 0, memtable);
        }
        memtable[previousRobbed][houseIdx] = res;
        return res;
    }
}