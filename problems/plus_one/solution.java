class Solution {
    public static int[] plusOne(int[] digits) {
        int i = digits.length - 1;
        if (digits[i] != 9) {
            digits[i]++;
            return digits;
        } else {
            while (i >= 0 && digits[i] == 9) 
                i--;
            if (i == -1) {
                int[] res = new int[digits.length + 1];
                res[0] = 1;
                return res;
            } else {
                digits[i]++;
                while (++i < digits.length)
                    digits[i] = 0;
                return digits;
            }
        }
    }
}