class Solution {
    public static boolean isHappy(int n) {
        long curr = n, next = 0;
        Set<Long> s = new HashSet<>();
        s.add(curr);
        do {
            int dgts = log10(curr) + 1;
            long pow = pow(10, dgts - 1);
            for (int i = 0; i < dgts; i++) {
                long dgt = curr / pow;
                curr -= ((curr / pow) * pow);
                pow /= 10;
                next += (dgt * dgt);
            }
            if (next == 1) 
                return true;
            if (s.contains(next))
                return false;
            s.add(next);
            curr = next;
            next = 0;
        } while (true);
    }
    
    private static int log10(long a) {
        long n = 10;
        int log10 = 0;
        while (n <= a) {
            log10++;
            n *= 10;
        }
        return log10;
    }
    
    private static long pow(int a, int b) {
        long res = 1, exp = b;
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