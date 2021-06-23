class Solution {
    private int longestPath;

    public int lengthLongestPath(final String input) {
        longestPath = 0;
        for (int i = -1; i < input.length(); i = lengthLongestPath(input, 0, i + 1, 0)) {}
        lengthLongestPath(input, 0, 0, 0);
        return longestPath;
    }

    private int lengthLongestPath(final String input, final int pathLengthSoFar, final int idx, final int depth) {
        int len = 0;
        for (int i = idx; i < input.length();) {
            final char c = input.charAt(i++);
            if (c == '\n') {
                int d = 0;
                while (i < input.length()) {
                    if (input.charAt(i) != '\t') {
                        break;
                    } else {
                        d++;
                        i++;
                    }
                }
                if (d == 0) {
                    return i - 1;
                } else if (d != depth + 1) {
                    return i - d - 1;
                } else {
                    i = lengthLongestPath(input, pathLengthSoFar + len, i, d);
                }
            } else {
                len++;
                if (c == '.') {
                    i++;
                    while (i < input.length()) {
                        final char next = input.charAt(i);
                        if (next != ' ' && next != '.' && !Character.isLetterOrDigit(next)) {
                            break;
                        } else {
                            len++;
                            i++;
                        }
                    }
                    longestPath = Math.max(longestPath, pathLengthSoFar + len + depth + 1);
                }
            }
        }
        return input.length();
    }
}