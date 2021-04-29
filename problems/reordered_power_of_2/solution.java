class Solution {
    public boolean reorderedPowerOf2(int n) {
        String s = Integer.toString(n);
        int[] digits = s.chars().map(asciiCode -> asciiCode - '0').toArray();
        boolean[] usedDigits = new boolean[digits.length];
        return canBePow2(digits, 0, 0, usedDigits);
    }

    private boolean canBePow2(
        final int[] digits,
        final int digitIdx,
        final int accum,
        final boolean[] usedDigits
    ) {
        if (digitIdx == digits.length)
            return Integer.bitCount(accum) == 1;
        for (int i = 0; i < digits.length; i++) {
            if (usedDigits[i] || (digitIdx == 0 && digits[i] == 0))
                continue;
            usedDigits[i] = true;
            if (canBePow2(digits, digitIdx + 1, accum + (digits[i] * pow(10, digits.length - digitIdx - 1)), usedDigits))
                return true;
            usedDigits[i] = false;
        }
        return false;
    }

    private int pow(final int n, final int pow) {
        switch (pow) {
            case 0:     return 1;
            case 1:     return n;
            case 2:     return n * n;
            case 3:     return n * n * n;
            case 4:     return n * n * n * n;
            case 5:     return n * n * n * n * n;
            case 6:     return n * n * n * n * n * n;
            case 7:     return n * n * n * n * n * n * n;
            case 8:     return n * n * n * n * n * n * n * n;
            case 9:     return n * n * n * n * n * n * n * n * n;
            case 10:    return n * n * n * n * n * n * n * n * n * n;
        }
        return -1;
    }
}