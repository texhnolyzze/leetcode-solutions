class Solution {
    
    public static boolean repeatedSubstringPattern(String s) {
        int[] p = prefixFunction(s);
        if (p[s.length() - 1] == 0)
            return false;
        int n = s.length() - p[s.length() - 1];
        return ((double) (s.length() - n) / n) % 1 == 0;
    }
    
    private static int[] prefixFunction(String s) {
        int[] p = new int[s.length()];
        p[0] = 0;
        outer: for (int i = 1; i < s.length(); i++) {
            int j = p[i - 1];
            while (j > 0) {
                if (s.charAt(j) == s.charAt(i)) {
                    p[i] = j + 1;
                    continue outer;
                }
                j = p[j - 1];
            }
            p[i] = s.charAt(0) == s.charAt(i) ? 1 : 0;
        }
        return p;
    }
    
}