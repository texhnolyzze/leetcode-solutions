class Solution {
    public static int compress(char[] chars) {
        int len = 0;
        for (int i = 0, writeToIndex = 0;;) {
            char c = chars[i];
            int cCount = 1;
            i++;
            while (i < chars.length && chars[i] == c) {
                i++;
                cCount++;
            }
            chars[writeToIndex++] = c;
            len++;
            if (cCount != 1) {
                int digitsNum = log10(cCount) + 1;
                int pow = pow(10, digitsNum - 1);
                for (int j = writeToIndex; j < writeToIndex + digitsNum; j++) {
                    chars[j] = (char) ('0' + cCount / pow);
                    cCount -= ((cCount / pow) * pow);
                    pow /= 10;
                    len++;
                }
                writeToIndex += digitsNum;
            }
            if (i == chars.length)
                break;
        }
        return len;
    }
    
    private static int log10(int a) {
        int n = 10;
        int log10 = 0;
        while (n <= a) {
            log10++;
            n *= 10;
        }
        return log10;
    }
    
    private static int pow(int a, int b) {
        int res = 1, exp = b;
        while (exp != 0) {
            if ((exp & 1) != 0)
                res *= a;
            exp >>= 1;
            if (exp == 0)
                break;
            a *= a;
        }
        return res;
    }
}