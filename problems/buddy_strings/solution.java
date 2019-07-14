class Solution {
    public static boolean buddyStrings(String a, String b) {
        if (a.length() != b.length())
            return false;
        char[] firstMismatch = null;
        char[] secondMismatch = null;
        for (int i = 0; i < a.length(); i++) {
            char aChar = a.charAt(i);
            char bChar = b.charAt(i);
            if (aChar != bChar) {
                if (firstMismatch == null) 
                    firstMismatch = new char[] {aChar, bChar};
                else {
                    if (secondMismatch == null)
                        secondMismatch = new char[] {aChar, bChar};
                    else
                        return false;
                }
            }
        }
        if (firstMismatch == null && secondMismatch == null) 
            return hasAtLeastTwoSameCharacter(a);
        return firstMismatch[0] == secondMismatch[1] && firstMismatch[1] == secondMismatch[0];
    }
    
    private static boolean hasAtLeastTwoSameCharacter(String s) {
        char[] countOf = new char[26];
        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            countOf[idx]++;
            if (countOf[idx] > 1)
                return true;
        }
        return false;
    }
    
}