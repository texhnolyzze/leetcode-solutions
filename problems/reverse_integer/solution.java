/**
 *
 * @author Texhnolyze
 */
public class Solution {
    
    public int reverse(int x) {
        if (x == 0 || x == Integer.MIN_VALUE)
            return 0;
        int[] digits = toDigits(x);
        reverse(digits);
        int pow = pow(10, digits.length - 1);
        int res = 0;
        long resLong = 0L;
        for (int digit : digits) {
            res += digit * pow;
            resLong += ((long) digit * (long) pow);
            pow /= 10;
        }
        return res == resLong ? (x < 0 ? -res : res) : 0;
    }
    
    private static int[] reverse(int[] arr) {
        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        return arr;
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