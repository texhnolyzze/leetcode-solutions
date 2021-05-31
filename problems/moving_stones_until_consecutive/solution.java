class Solution {
    public int[] numMovesStones(int a, int b, int c) {
        final List<Integer> list = new ArrayList<>(List.of(a, b, c));
        list.sort(Comparator.naturalOrder());
        a = list.get(0);
        b = list.get(1);
        c = list.get(2);
        final int[][][] memtable = new int[101][101][101];
        invalidate(memtable);
        final int minSteps = search(a, b, c, memtable, true);
        invalidate(memtable);
        final int maxSteps = search(a, b, c, memtable, false);
        return new int[] {minSteps, maxSteps};
    }

    private int search(
        final int a,
        final int b,
        final int c,
        final int[][][] memtable,
        final boolean searchMin
    ) {
        if (c - b == 1 && b - a == 1)
            return 0;
        int res = memtable[a][b][c];
        if (res != -1)
            return res;
        res = searchMin ? Integer.MAX_VALUE : -1;
        for (int i = a + 1; i < b; i++) {
            final int steps = 1 + search(i, b, c, memtable, searchMin);
            res = searchMin ? Math.min(res, steps) : Math.max(res, steps);
        }
        for (int i = b + 1; i < c; i++) {
            final int steps = 1 + search(a, b, i, memtable, searchMin);
            res = searchMin ? Math.min(res, steps) : Math.max(res, steps);
        }
        for (int i = b + 1; i < c; i++) {
            final int steps = 1 + search(b, i, c, memtable, searchMin);
            res = searchMin ? Math.min(res, steps) : Math.max(res, steps);
        }
        for (int i = b - 1; i > a; i--) {
            final int steps = 1 + search(a, i, b, memtable, searchMin);
            res = searchMin ? Math.min(res, steps) : Math.max(res, steps);
        }
        memtable[a][b][c] = res;
        return res;
    }

    private void invalidate(final int[][][] memtable) {
        for (int i = 0; i < memtable.length; i++) {
            for (int j = 0; j < memtable[i].length; j++) {
                Arrays.fill(memtable[i][j], -1);
            }
        }
    }
}