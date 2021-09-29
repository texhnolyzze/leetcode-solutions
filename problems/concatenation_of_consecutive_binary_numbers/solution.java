class Solution {
    private static final int MOD = (int) (1E9 + 7);

    public int concatenatedBinary(final int n) {
        if (n == 1) {
            return 1;
        }
        final long sub = concatenatedBinary(n - 1);
        return (int) (((sub << highestSetBitIdx(n) + 1) | n) % MOD);
    }

    private long highestSetBitIdx(final long n) {
        long temp = n;
        int res = -1;
        while (temp != 0) {
            temp >>>= 1;
            res++;
        }
        return res;
    }
}