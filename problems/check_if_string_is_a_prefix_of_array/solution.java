class Solution {
    public boolean isPrefixString(
        final String s,
        final String[] words
    ) {
        String word = words[0];
        int wordIdx = 0;
        int wordPos = 0;
        for (int i = 0;;) {
            final int c1 = s.charAt(i++);
            final int c2 = word.charAt(wordPos++);
            if (c1 != c2) {
                return false;
            }
            if (i == s.length()) {
                return wordPos == word.length();
            } else {
                if (wordPos == word.length()) {
                    wordIdx++;
                    if (wordIdx == words.length) {
                        return false;
                    }
                    wordPos = 0;
                    word = words[wordIdx];
                }
            }
        }
    }
}