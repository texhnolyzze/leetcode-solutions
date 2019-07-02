class Solution {
    public static String removeOuterParentheses(String s) {
        StringBuilder res = new StringBuilder();
        for (int i = 0, stackSize = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ')')
                stackSize--;
            if (stackSize > 0)
                res.append(c);
            if (c == '(')
                stackSize++;
        }
        return res.toString();
    }
}