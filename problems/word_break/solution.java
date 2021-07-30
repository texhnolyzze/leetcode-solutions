class Solution {
    public boolean wordBreak(
        final String s,
        final List<String> wordDict
    ) {
        wordDict.sort(String::compareTo);
        final Boolean[] segmentable = new Boolean[s.length()];
        return wordBreak(s, wordDict, 0, segmentable);
    }

    private boolean wordBreak(
        final String s,
        final List<String> dictionary,
        final int from,
        final Boolean[] segmentable
    ) {
        if (from >= s.length()) {
            return true;
        }
        Boolean res = segmentable[from];
        if (res != null) {
            return res;
        }
        res = false;
        for (int i = from; i < s.length(); i++) {
            final String substring = s.substring(from, i + 1);
            if (Collections.binarySearch(dictionary, substring) >= 0) {
                final boolean sub = wordBreak(s, dictionary, i + 1, segmentable);
                if (sub) {
                    res = true;
                    break;
                }
            }
        }
        segmentable[from] = res;
        return res;
    }
}