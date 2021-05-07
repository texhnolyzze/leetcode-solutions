class Solution {
    public int longestPalindromeSubseq(String s) {
        int[][] memtable = new int[s.length()][s.length()];
        for (final int[] arr : memtable) {
            Arrays.fill(arr, -1);
        }
        return longestPalindromeSubseq(s, 0, s.length() - 1, memtable);
    }

    private int longestPalindromeSubseq(
        final String s,
        final int i,
        final int j,
        final int[][] memtable
    ) {
        if (j < i)
            return 0;
        if (i == j)
            return 1;
        int res = memtable[i][j];
        if (res != -1)
            return res;
        int lcsWithoutLeftCharacter = longestPalindromeSubseq(s, i + 1, j, memtable);
        int lcsWithoutRightCharacter = longestPalindromeSubseq(s, i, j - 1, memtable);
        int lcsWithoutBothCharacters = longestPalindromeSubseq(s, i + 1, j - 1, memtable);
        res = Math.max(
            lcsWithoutLeftCharacter,
            Math.max(
                lcsWithoutRightCharacter,
                lcsWithoutBothCharacters + (s.charAt(i) == s.charAt(j) ? 2 : 0)
            )
        );
        memtable[i][j] = res;
        return res;
    }
}