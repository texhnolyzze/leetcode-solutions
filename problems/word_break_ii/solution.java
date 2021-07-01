class Solution {
    public List<String> wordBreak(
        final String s,
        final List<String> wordDict
    ) {
        final TreeSet<StringBuilder> dict = wordDict.stream().map(StringBuilder::new).collect(Collectors.toCollection(TreeSet::new));
        return wordBreak(s, dict, 0);
    }

    private List<String> wordBreak(
        final String s,
        final TreeSet<StringBuilder> wordDict,
        final int idx
    ) {
        final List<String> res = new ArrayList<>();
        final StringBuilder builder = new StringBuilder();
        for (int i = idx; i < s.length(); i++) {
            builder.append(s.charAt(i));
            if (wordDict.contains(builder)) {
                if (i != s.length() - 1) {
                    final List<String> nested = wordBreak(s, wordDict, i + 1);
                    final String str = builder.toString();
                    for (final String nestedStr : nested) {
                        res.add(str + " " + nestedStr);
                    }
                } else {
                    res.add(builder.toString());
                }
            }
        }
        return res;
    }
}