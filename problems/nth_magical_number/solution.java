class Solution {
    private static final int MOD = (int) (1E9 + 7);

    public int nthMagicalNumber(
        final int n,
        int a,
        int b
    ) {
        if (a > b) {
            final int temp = a;
            a = b;
            b = temp;
        }
        long serial = 0;
        long aMultiplier = 2;
        long bMultiplier = 2;
        final long ds;
        final long dn;
        long p1 = a;
        long p2 = b;
        while (true) {
            serial++;
            if (serial == n) {
                return (int) p1;
            }
            if (p1 % a == 0) {
                if (p1 % b == 0) {
                    ds = serial;
                    dn = p1;
                    break;
                } else {
                    final long next = a * aMultiplier++;
                    if (next < p2) {
                        p1 = next;
                    } else {
                        final long temp = p2;
                        p2 = next;
                        p1 = temp;
                    }
                }
            } else {
                final long next = b * bMultiplier++;
                if (next < p2) {
                    p1 = next;
                } else {
                    final long temp = p2;
                    p2 = next;
                    p1 = temp;
                }
            }
        }
        serial = ds * (n / ds);
        if (serial == n) {
            return (int) moduloMultiplication(dn, n / ds);
        } else {
            aMultiplier = (aMultiplier - 1) * (n / ds) + 1;
            bMultiplier = (bMultiplier - 1) * (n / ds) + 1;
            p1 = a * aMultiplier++;
            p2 = b * bMultiplier++;
            if (p1 > p2) {
                final long temp = p1;
                p1 = p2;
                p2 = temp;
            }
        }
        while (true) {
            if (++serial == n) {
                return (int) (p1 % MOD);
            }
            if (p1 % a == 0) {
                final long next = a * aMultiplier++;
                if (next < p2) {
                    p1 = next;
                } else {
                    final long temp = p2;
                    p2 = next;
                    p1 = temp;
                }
            } else {
                final long next = b * bMultiplier++;
                if (next < p2) {
                    p1 = next;
                } else {
                    final long temp = p2;
                    p2 = next;
                    p1 = temp;
                }
            }
        }
    }

    private long moduloMultiplication(
        long a,
        long b
    ) {
        long res = 0;
        a %= MOD;
        while (b > 0) {
            if ((b & 1) > 0) {
                res = (res + a) % MOD;
            }
            a = (2 * a) % MOD;
            b >>= 1;
        }
        return res;
    }
}