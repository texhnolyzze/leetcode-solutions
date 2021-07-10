class Solution {
    public int minTaps(
        final int n,
        final int[] ranges
    ) {
        int left = 0;
        int open = 0;
        while (left < n) {
            int rightmost = -1;
            for (int i = 0; i < ranges.length; i++) {
                if (ranges[i] == 0) {
                    continue;
                }
                final int l = i - ranges[i];
                final int r = i + ranges[i];
                if (
                    l <= left &&
                    left <= r &&
                    (rightmost == -1 || rightmost + ranges[rightmost] < r)
                ) {
                    rightmost = i;
                }
                if (i - 100 > left) {
                    break;
                }
            }
            if (rightmost == -1) {
                return -1;
            }
            final int nextLeft = rightmost + ranges[rightmost];
            if (nextLeft == left) {
                return -1;
            }
            left = nextLeft;
            open++;
        }
        return open;
    }
}