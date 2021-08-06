class Solution {
    private final int unknown = -1;
    private final int noSolution = -2;

    public int longestValidParentheses(String s) {
        final int[] memtable = new int[s.length()];
        Arrays.fill(memtable, unknown);
        int longest = 0;
        for (int i = 0; i < s.length(); i++) {
            longest = Math.max(longest, longestValidParentheses(s, i, memtable));
        }
        return longest;
    }

    private int longestValidParentheses(
        final String s,
        final int idx,
        final int[] memtable
    ) {
        if (idx <= 0 || s.charAt(idx) == '(') {
            return noSolution;
        }
        int res = memtable[idx];
        if (res != unknown) {
            return res;
        }
        if (s.charAt(idx - 1) == ')') {
            final int subLen1 = longestValidParentheses(s, idx - 1, memtable);
            if (subLen1 != noSolution) {
                final int expectedOpeningParenthesisIdx = idx - subLen1 - 1;
                if (expectedOpeningParenthesisIdx >= 0 && s.charAt(expectedOpeningParenthesisIdx) == '(') {
                    res = 2 + subLen1;
                    final int subLen2 = longestValidParentheses(s, expectedOpeningParenthesisIdx - 1, memtable);
                    if (subLen2 != noSolution) {
                        res += subLen2;
                    }
                } else {
                    res = noSolution;
                }
            } else {
                res = noSolution;
            }
        } else {
            res = 2;
            final int subLen = longestValidParentheses(s, idx - 2, memtable);
            if (subLen != noSolution) {
                res += subLen;
            }
        }
        memtable[idx] = res;
        return res;
    }
}