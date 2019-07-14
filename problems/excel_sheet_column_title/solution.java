class Solution {
     public static String convertToTitle(int n) {
        long curr = n;
        int numDigits = getNumDigits(curr);
        int digitsRemained = numDigits;
        long pow = pow(26, numDigits - 1);
        StringBuilder sb = new StringBuilder();
        while (digitsRemained > 0) {
            System.out.println(curr);
            int d = curr != 26 && curr % 26 == 0 ? 1 : 0;
            long digit = (curr / pow) - d;
            sb.append((char) ('A' + digit - 1));
            curr -= (digit * pow);
            pow /= 26;
            digitsRemained--;
        }
        return sb.toString();
    }
    
    private static int getNumDigits(long n) {
        long rightBound = 26;
        int numDigits = 1;
        while (true) {
            if ((n <= rightBound))
                return numDigits;
            numDigits++;
            rightBound = 26 + 26 * rightBound;
        }
    }
    
    private static long pow(int a, int b) {
        if (b == 0)
            return 1;
        long res = a;
        for (int i = 0; i < b - 1; i++) 
            res *= a;
        return res;
    }
}