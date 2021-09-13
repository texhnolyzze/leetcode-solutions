class Solution {
    public int minFlips(
        final int a,
        final int b,
        final int c
    ) {
        final int or = a | b;
        int flips = 0;
        for (int i = 31; i >= 0; i--) {
            final int mask = 1 << i;
            if ((c & mask) == 0) {
                if ((or & mask) != 0) {
                    if ((a & mask) == 0 || (b & mask) == 0) {
                        flips++;
                    } else {
                        flips += 2;
                    }
                }
            } else {
                if ((or & mask) == 0) {
                    flips++;
                }
            }
        }
        return flips;
    }
}