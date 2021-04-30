class Solution {
    private int targetPermutationIdx;
    private int currentPermutationIdx;

    public String getPermutation(int n, int k) {
        this.targetPermutationIdx = k;
        this.currentPermutationIdx = 0;
        int[] digits = new int[n];
        for (int i = 0; i < n; i++) {
            digits[i] = i + 1;
        }
        boolean[] usedDigits = new boolean[n];
        return getPermutation(
            digits,
            usedDigits,
            0,
            0
        );
    }

    private String getPermutation(
        final int[] digits,
        final boolean[] usedDigits,
        final int accum,
        final int numDigitsSoFar
    ) {
        if (numDigitsSoFar == digits.length) {
            currentPermutationIdx++;
            if (currentPermutationIdx == targetPermutationIdx)
                return Integer.toString(accum);
            else
                return null;
        } else {
            for (int i = 0; i < digits.length; i++) {
                if (usedDigits[i])
                    continue;
                usedDigits[i] = true;
                String permutation = getPermutation(
                    digits,
                    usedDigits,
                    accum + digits[i] * pow10(digits.length - numDigitsSoFar - 1),
                    numDigitsSoFar + 1
                );
                if (permutation != null)
                    return permutation;
                usedDigits[i] = false;
            }
            return null;
        }
    }

    private int pow10(final int n) {
        return switch (n) {
            case 0 -> 1;
            case 1 -> 10;
            case 2 -> 100;
            case 3 -> 1000;
            case 4 -> 10000;
            case 5 -> 100000;
            case 6 -> 1000000;
            case 7 -> 10000000;
            case 8 -> 100000000;
            case 9 -> 1000000000;
            default -> -1;
        };
    }
}