class Solution {
    public int strStr(String haystack, String needle) {
        return indexOfRabinCarp(haystack, needle);
    }
    
    private static final int PRIME = 113;
    
    private static int hash(String s, int l, int r) {
	int hash = 0;
	for (int i = l; i <= r; i++) 
            hash = PRIME * hash + s.charAt(i);
	return hash;
    }
    
    private static boolean check(String s, String pattern, int l, int r) {
	for (int i = l, j = 0; i <= r; i++, j++) {
            if (s.charAt(i) != pattern.charAt(j))
                return false;
	}
	return true;
    }
    
    private static int indexOfRabinCarp(String s, String pattern) {
        int n = s.length(), m = pattern.length();
        if ((n | m) == 0)
            return 0;
        if (n == 0 || n < m)
            return -1;
        if (m == 0)
            return 0;
	int p_pow = pow(PRIME, m - 1);
	int patt_hash = hash(pattern, 0, m - 1);
	int s_hash = hash(s, 0, 0 + m - 1);
	if (s_hash == patt_hash) {
            if (check(s, pattern, 0, 0 + m - 1))
                return 0;
	}
	for (int i = 1; i <= n - m; i++) {
            s_hash = (s_hash - s.charAt(i - 1) * p_pow) * PRIME + s.charAt(i + m - 1);
            if (s_hash == patt_hash) {
                if (check(s, pattern, i, i + m - 1))
                    return i;
            }
	}
	return -1;
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