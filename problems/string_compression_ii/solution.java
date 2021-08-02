class Solution {
    public int getLengthOfOptimalCompression(
        final String s,
        final int k
    ) {
        final RunLengthPart[] parts = compress(s);
        final int[][][] optimal = new int[s.length()][s.length()][k + 1];
        for (int i = 0; i < optimal.length; i++) {
            for (int j = 0; j < optimal[i].length; j++) {
                Arrays.fill(optimal[i][j], Integer.MAX_VALUE);
            }
        }
        return getLengthOfOptimalCompression(parts, 0, parts.length - 1, k, optimal);
    }

    private RunLengthPart[] compress(final String s) {
        final List<RunLengthPart> res = new ArrayList<>();
        char prev = s.charAt(0);
        int n = 1;
        for (int i = 1; i < s.length(); i++) {
            final char curr = s.charAt(i);
            if (curr == prev) {
                n++;
            } else {
                res.add(new RunLengthPart(prev, n));
                prev = curr;
                n = 1;
            }
        }
        res.add(new RunLengthPart(prev, n));
        return res.toArray(RunLengthPart[]::new);
    }

    private int getLengthOfOptimalCompression(
        final RunLengthPart[] parts,
        final int from,
        final int to,
        final int k,
        final int[][][] optimal
    ) {
        final int len = to - from + 1;
        if (len <= 0) {
            return 0;
        }
        int res = optimal[from][to][k];
        if (res != Integer.MAX_VALUE) {
            return res;
        }
        final RunLengthPart leftPart = parts[from];
        for (int i = 0; i <= Math.min(leftPart.n, k); i++) {
            res = Math.min(
                res,
                encodedLength(leftPart.n, i) + getLengthOfOptimalCompression(parts, from + 1, to, k - i, optimal)
            );
        }
        int kRemained = k;
        int nTotal = leftPart.n;
        int leftBound = from + 1;
        outer: for (int i = from + 2; i <= to; i++) {
            final RunLengthPart part = parts[i];
            if (part.c == leftPart.c) {
                for (int j = 1; j <= kRemained; j++) {
                    final int sub = getLengthOfOptimalCompression(parts, leftBound, i - 1, j, optimal);
                    if (sub == 0) {
                        nTotal += part.n;
                        kRemained -= j;
                        leftBound = i + 1;
                        for (int l = 0; l <= kRemained; l++) {
                            res = Math.min(
                                res,
                                encodedLength(nTotal, l) + getLengthOfOptimalCompression(parts, i + 1, to, kRemained - l, optimal)
                            );
                        }
                        break;
                    } else if (j == kRemained) {
                        break outer;
                    }
                }
            }
        }
        optimal[from][to][k] = res;
        return res;
    }

    private int encodedLength(
        final int n,
        final int deletions
    ) {
        final int remained = n - deletions;
        if (remained <= 0) {
            return 0;
        } else if (remained == 1) {
            return 1;
        } else if (remained <= 9) {
            return 2;
        } else if (remained <= 99) {
            return 3;
        } else if (remained == 100) {
            return 4;
        } else {
            throw new IllegalStateException();
        }
    }

    private static class RunLengthPart {

        final char c;
        final int n;

        private RunLengthPart(
            final char c,
            final int n
        ) {
            this.c = c;
            this.n = n;
        }

    }
}