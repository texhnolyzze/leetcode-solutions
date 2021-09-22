class Solution {
    public String minInteger(
        final String num,
        final int k
    ) {
        int remained = k;
        final char[] min = num.toCharArray();
        final int n = min.length;
        int leftBound = 0;
        while (leftBound < n && remained != 0) {
            char minChar = min[leftBound];
            int minPos = leftBound;
            for (int i = leftBound + 1; i < n; i++) {
                final char c = min[i];
                if (c < minChar && i - leftBound <= remained) {
                    minChar = c;
                    minPos = i;
                }
            }
            if (minPos == leftBound) {
                leftBound++;
            } else {
                System.arraycopy(min, leftBound, min, leftBound + 1, minPos - leftBound);
                min[leftBound] = minChar;
                remained -= (minPos - leftBound);
                leftBound++;
            }
        }
        return new String(min);
    }
}