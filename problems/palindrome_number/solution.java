class Solution {
    
    public boolean isPalindrome(int x) {
        if (x < 0)
            return false;
        if (x == 0)
            return true;
        int[] digits = toDigits(x);
        for (int i = 0, j = digits.length - 1; i < j; i++, j--) {
            if (digits[i] != digits[j])
                return false;
        }
        return true;
    }
    
    private static int[] toDigits(int n) {
        int x = Math.abs(n);
        int[] digits = new int[(int) Math.log10(x) + 1];
        int pow = pow(10, digits.length - 1);
        for (int i = 0; i < digits.length; i++) {
            digits[i] = x / pow;
            x -= digits[i] * pow;
            pow /= 10;
        }
        return digits;
    }
    
    private static int pow(int a, int b) {
        if (b == 0)
            return 1;
        int res = a;
        for (int i = 0; i < b - 1; i++) 
            res *= a;
        return res;
    }
    
}