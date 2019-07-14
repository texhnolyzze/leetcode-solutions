class Solution {
    public int titleToNumber(String s) {
        int num = 0;
        int pow = 1;
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            num += (c - 'A' + 1) * pow;
            pow *= 26;
        }
        return num;
    }
}