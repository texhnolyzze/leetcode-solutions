class Solution {
    public int longestCommonSubsequence(
        String s1,
        String s2
    ) {
        int[][] memtable = new int[s1.length() + 1][s2.length() + 1];
        for (int[] arr : memtable) {
            Arrays.fill(arr, -1);
        }
        return lcs(
            s1,
            s2,
            s1.length(),
            s2.length(),
            memtable
        );
    }

    private int lcs(
        String s1,
        String s2,
        int s1Length,
        int s2Length,
        int[][] memtable
    ) {
        int res = memtable[s1Length][s2Length];
        if (res != -1)
            return res;
        if (s1Length == 0) {
            res = 0;
        } else if (s2Length == 0) {
            res = 0;
        } else {
            int n1 = lcs(s1, s2, s1Length - 1, s2Length, memtable);
            int n2 = lcs(s1, s2, s1Length, s2Length - 1, memtable);
            int n3 = lcs(s1, s2, s1Length - 1, s2Length - 1, memtable);
            res = Math.max(
                n1,
                Math.max(
                    n2,
                    n3 + (s1.charAt(s1Length - 1) == s2.charAt(s2Length - 1) ? 1 : 0)
                )
            );
        }
        memtable[s1Length][s2Length] = res;
        return res;
    }
}