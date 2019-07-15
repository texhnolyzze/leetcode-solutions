class Solution {
    public char findTheDifference(String s, String t) {
        int diffChar = 0;
        for (int i = 0; i < s.length(); i++) {
            diffChar ^= s.charAt(i);
            diffChar ^= t.charAt(i);
        }
        diffChar ^= t.charAt(t.length() - 1);
        return (char) diffChar;
    }
}