import java.math.BigInteger;

class Solution {
    public boolean splitString(final String s) {
        final int n = s.length();
        final BigInteger[][] splits = new BigInteger[n][n + 1];
        for (int i = 0; i < n; i++) {
            for (int len = 1; len <= n - i; len++) {
                splits[i][len] = new BigInteger(s.substring(i, i + len));
            }
        }
        for (int len = 1; len <= n - 1; len++) {
            final BigInteger split = splits[0][len];
            if (
                backtrack(
                    splits,
                    n - len,
                    split
                )
            ) {
                return true;
            }
        }
        return false;
    }

    private boolean backtrack(
        final BigInteger[][] splits,
        final int remainingLength,
        final BigInteger prev
    ) {
        if (remainingLength == 0) {
            return true;
        } else {
            final int n = splits.length;
            final int idx = n - remainingLength;
            for (int len = 1; len <= remainingLength; len++) {
                final BigInteger next = splits[idx][len];
                if (
                    prev.subtract(next).equals(BigInteger.ONE) &&
                    backtrack(
                        splits,
                        remainingLength - len,
                        next
                    )
                ) {
                    return true;
                }
            }
            return false;
        }
    }
}