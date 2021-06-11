class Solution {
    public boolean canConstruct(final String s, final int k) {
        final int[] freq = new int[26];
        for (int i = 0; i < s.length(); i++) {
            final char c = s.charAt(i);
            freq[c - 'a']++;
        }
        int numOddLetters = 0;
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] % 2 != 0) {
                numOddLetters++;
            }
        }
        if (numOddLetters > k) {
            return false;
        } else if (numOddLetters == k) {
            return true;
        } else {
            int numPalindromesSoFar = numOddLetters;
            for (int i = 0; i < freq.length; i++) {
                if (freq[i] % 2 != 0)
                    freq[i]--;
            }
            for (int i = 0; i < freq.length; i++) {
                for (int j = 0; j < freq[i];) {
                    if (k - numPalindromesSoFar == 1) {
                        j++;
                        numPalindromesSoFar++;
                    } else {
                        j += 2;
                        numPalindromesSoFar += 2;
                    }
                    if (numPalindromesSoFar == k) {
                        return true;
                    }
                }
            }
            return false;
        }
    }
}