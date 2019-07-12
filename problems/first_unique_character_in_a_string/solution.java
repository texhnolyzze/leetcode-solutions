class Solution {
    public int firstUniqChar(String s) {
        int n = s.length();
        char[] countOf = new char[27];
        int[] firstIndexOf = new int[27];
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            countOf[c - 'a']++;
            firstIndexOf[c - 'a'] = min(n, i);
        }
        int minIndex = n;
        for (int i = 0; i < 27; i++) {
            if (countOf[i] == 1)
                minIndex = min(minIndex, firstIndexOf[i]);
        }
        return minIndex == n ? -1 : minIndex;
    }
    
    private static int min(int a, int b) {
        return a - ((a - b) & ((b - a) >> 31));
    }
}