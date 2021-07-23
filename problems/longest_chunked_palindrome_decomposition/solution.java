class Solution {
    public int longestDecomposition(final String text) {
        final int[] longest = new int[text.length()];
        return longestDecomposition(text, 0, longest);
    }

    private int longestDecomposition(
        final String text,
        final int from,
        final int[] longest
    ) {
        final int to = text.length() - from - 1;
        if (to < from) {
            return 0;
        }
        int max = longest[from];
        if (max != 0) {
            return max;
        }
        max = 1;
        for (int i = from; i < text.length() / 2; i++) {
            final int len = i - from + 1;
            boolean matches = true;
            for (int j = 0; j < len; j++) {
                if (text.charAt(from + j) != text.charAt(to - len + j + 1)) {
                    matches = false;
                    break;
                }
            }
            if (matches) {
                max = Math.max(
                    max,
                    2 + longestDecomposition(
                        text,
                        i + 1,
                        longest
                    )
                );
            }
        }
        longest[from] = max;
        return max;
    }
}