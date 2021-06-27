class Solution {
    public int strangePrinter(final String s) {
        final int[][][] memtable = new int[s.length()][s.length()][27];
        for (int i = 0; i < memtable.length; i++) {
            for (int j = 0; j < memtable[i].length; j++) {
                Arrays.fill(memtable[i][j], -1);
            }
        }
        return strangePrinter(s, 0, s.length() - 1, (char) ('a' - 1), memtable);
    }

    private int strangePrinter(
        final String s,
        final int left,
        final int right,
        final char c,
        final int[][][] memtable
    ) {
        if (left > right) {
            return 0;
        } else {
            final int ops = c == s.charAt(left) ? 0 : 1;
            if (left == right) {
                return ops;
            } else {
                int min = memtable[left][right][c - ('a' - 1)];
                if (min != -1) {
                    return min;
                }
                min = ops + strangePrinter(s, left + 1, right, s.charAt(left), memtable);
                for (int i = left; i < right; i++) {
                    min = Math.min(
                        min,
                        ops + strangePrinter(s, left, i, s.charAt(left), memtable) + strangePrinter(s, i + 1, right, c, memtable)
                    );
                }
                memtable[left][right][c - ('a' - 1)] = min;
                return min;
            }
        }
    }
}