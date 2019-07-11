class Solution {
    public static int longestPalindrome(String s) {
        Map<Character, Integer> countOf = new HashMap<>();
        for (int i = 0; i < s.length(); i++)
            countOf.merge(s.charAt(i), 1, Integer::sum);
        int longestPalindromeLen = 0;
        boolean hasUnpairedCharacter = false;
        for (int count : countOf.values()) {
            if (count > 1) {
                if (count % 2 == 0) 
                    longestPalindromeLen += count;
                else {
                    hasUnpairedCharacter = true;                    
                    longestPalindromeLen += (count - 1);
                }
            } else
                hasUnpairedCharacter = true;
        }
        return hasUnpairedCharacter ? longestPalindromeLen + 1 : longestPalindromeLen;
    }
}