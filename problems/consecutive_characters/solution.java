class Solution {
    public int maxPower(final String s) {
        int max = Integer.MIN_VALUE;
        char curr = s.charAt(0);
        int currLen = 1;
        for (int i = 1; i < s.length(); i++) {
            final char c = s.charAt(i);
            if (c == curr) {
                currLen++;
            } else {
                max = Math.max(
                    max,
                    currLen
                );
                currLen = 1;
                curr = c;
            }
        }
        max = Math.max(
            max,
            currLen
        );
        return max;
    }
}