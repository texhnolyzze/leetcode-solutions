class Solution {
    private static final int MOD = (int) (1E9 + 7);

    private final int numGoodEvenDigits = 5;
    private final int numGoodOddDigits = 4;

    public int countGoodNumbers(final long n) {
        if (n == 1) {
            return numGoodEvenDigits;
        }
        return (int) countGoodNumbers0(n, true);
    }

    public long countGoodNumbers0(
        final long n,
        final boolean firstEven
    ) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return firstEven ? numGoodEvenDigits : numGoodOddDigits;
        } else if (n == 2) {
            return numGoodEvenDigits * numGoodOddDigits;
        } else if (n == 3) {
            return (firstEven ? numGoodEvenDigits : numGoodOddDigits) * numGoodEvenDigits * numGoodOddDigits;
        } else {
            final long res;
            if (n % 2 == 0) {
                final long left;
                final long right;
                if (n / 2 % 2 == 0) {
                    left = countGoodNumbers0(n / 2, firstEven);
                    right = left;
                } else {
                    left = countGoodNumbers0(n / 2 - 1, firstEven);
                    right = left * numGoodOddDigits * numGoodEvenDigits % MOD;
                }
                res = left * right % MOD;
            } else {
                res = (firstEven ? numGoodEvenDigits : numGoodOddDigits) * countGoodNumbers0(n - 1, !firstEven) % MOD;
            }
            return res;
        }
    }
}