class Solution {
    public int countNumbersWithUniqueDigits(final int n) {
        int bound = 1;
        for (int i = 0; i < n; i++) {
            bound *= 10;
        }
        int res = 1;
        for (int i = 1; i <= 9; i++) {
            res += countNumbersWithUniqueDigits(bound, i, 1 << i);
        }
        return res;
    }

    private int countNumbersWithUniqueDigits(
        final int bound,
        final int num,
        final int used
    ) {
        if (num >= bound) {
            return 0;
        }
        int res = 1;
        for (int i = 0; i <= 9; i++) {
            if ((used & (1 << i)) == 0) {
                res += countNumbersWithUniqueDigits(bound, num * 10 + i, used | (1 << i));
            }
        }
        return res;
    }
}