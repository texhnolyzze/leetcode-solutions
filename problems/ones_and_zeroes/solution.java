class Solution {
    public int findMaxForm(
        final String[] strs,
        final int m,
        final int n
    ) {
        final int len = strs.length;
        final int[] onesAndZeroes = new int[len];
        for (int i = 0; i < len; i++) {
            final String str = strs[i];
            int ones = 0;
            int zeroes = 0;
            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) == '1') {
                    ones++;
                } else {
                    zeroes++;
                }
            }
            onesAndZeroes[i] = ones << 16 | zeroes;
        }
        final int[][][] memtable = new int[len][m + 1][n + 1];
        for (int i = 0; i < memtable.length; i++) {
            for (int j = 0; j < memtable[i].length; j++) {
                Arrays.fill(memtable[i][j], -1);
            }
        }
        return findMaxForm(onesAndZeroes, m, n, len - 1, memtable);
    }

    private int findMaxForm(
        final int[] onesAndZeroes,
        final int m,
        final int n,
        final int idx,
        final int[][][] memtable
    ) {
        if (idx < 0) {
            return 0;
        }
        int res = memtable[idx][m][n];
        if (res != -1) {
            return res;
        }
        final int ones = onesAndZeroes[idx] >>> 16;
        final int zeroes = onesAndZeroes[idx] & 0xffff;
        if (idx == 0) {
            res = ones <= n && zeroes <= m ? 1 : 0;
        } else {
            res = findMaxForm(onesAndZeroes, m, n, idx - 1, memtable);
            if (ones <= n && zeroes <= m) {
                res = Math.max(
                    res,
                    1 + findMaxForm(
                        onesAndZeroes,
                        m - zeroes,
                        n - ones,
                        idx - 1,
                        memtable
                    )
                );
            }
        }
        memtable[idx][m][n] = res;
        return res;
    }
}