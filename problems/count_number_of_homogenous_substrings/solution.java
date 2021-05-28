class Solution {
    public int countHomogenous(final String s) {
        final int mod = 1000000007;
        final int[][] letterHomogenousSubstringCounts = new int[26][s.length() + 1];
        char prevLetter = s.charAt(0);
        int prevLetterIdx = 0;
        for (int idx = 1; idx < s.length(); idx++) {
            final char c = s.charAt(idx);
            if (c != prevLetter) {
                letterHomogenousSubstringCounts[prevLetter - 'a'][idx - prevLetterIdx]++;
                prevLetter = c;
                prevLetterIdx = idx;
            }
        }
        letterHomogenousSubstringCounts[prevLetter - 'a'][s.length() - prevLetterIdx]++;
        int res = 0;
        for (final int[] letterHomogenousSubstringCount : letterHomogenousSubstringCounts) {
            for (int length = 1; length < letterHomogenousSubstringCount.length; length++) {
                final int count = letterHomogenousSubstringCount[length];
                if (count == 0)
                    continue;
                final long mul = (long) length * (length + 1) / 2;
                res = (int) ((res + (count * mul) % mod) % mod);
            }
        }
        return res;
    }
}