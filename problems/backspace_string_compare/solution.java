class Solution {
    public boolean backspaceCompare(String s, String t) {
        StringBuilder sBuilder = new StringBuilder();
        StringBuilder tBuilder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '#') sBuilder.setLength(Math.max(0, sBuilder.length() - 1));
            else sBuilder.append(c);
        }
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (c == '#') tBuilder.setLength(Math.max(0, tBuilder.length() - 1));
            else tBuilder.append(c);
        }
        return sBuilder.toString().equals(tBuilder.toString());
    }
}