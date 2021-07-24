class Solution {
    public String maximumTime(final String time) {
        final int[] digits = new int[4];
        digits[0] = time.charAt(0) == '?' ? -1 : Integer.parseInt(String.valueOf(time.charAt(0)));
        digits[1] = time.charAt(1) == '?' ? -1 : Integer.parseInt(String.valueOf(time.charAt(1)));
        digits[2] = time.charAt(3) == '?' ? -1 : Integer.parseInt(String.valueOf(time.charAt(3)));
        digits[3] = time.charAt(4) == '?' ? -1 : Integer.parseInt(String.valueOf(time.charAt(4)));
        final int[] max = new int[4];
        maximumTime(digits, 0, max);
        return new StringBuilder().append(max[0]).append(max[1]).append(':').append(max[2]).append(max[3]).toString();
    }

    private int[] maximumTime(
        final int[] digits,
        final int idx,
        final int[] max
    ) {
        if (idx > 3) {
            if (max[0] == -1 || Arrays.compare(digits, max) > 0) {
                System.arraycopy(digits, 0, max, 0, digits.length);
            }
            return max;
        }
        if (digits[idx] != -1) {
            return maximumTime(digits, idx + 1, max);
        }
        if (idx == 0) {
            digits[0] = 0;
            maximumTime(digits, 1, max);
            digits[0] = 1;
            maximumTime(digits, 1, max);
            if (digits[1] < 4) {
                digits[0] = 2;
                maximumTime(digits, 1, max);
            }
        } else if (idx == 1) {
            for (int i = 0; i < (digits[0] == 2 ? 4 : 10); i++) {
                digits[1] = i;
                maximumTime(digits, 2, max);
            }
        } else if (idx == 2) {
            for (int i = 0; i < 6; i++) {
                digits[2] = i;
                maximumTime(digits, 3, max);
            }
        } else {
            for (int i = 0; i < 10; i++) {
                digits[3] = i;
                maximumTime(digits, 4, max);
            }
        }
        digits[idx] = -1;
        return max;
    }
}