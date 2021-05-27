class Solution {
    public int maxDepth(String s) {
        int open = 0;
        int maxDepth = 0;
        for (int i = 0; i < s.length(); i++) {
            final char c = s.charAt(i);
            if (c == '(')
                open++;
            else if (c == ')') {
                maxDepth = Math.max(maxDepth, open);
                open--;
            }
        }
        return maxDepth;
    }
}