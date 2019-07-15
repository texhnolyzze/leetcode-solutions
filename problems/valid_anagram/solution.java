class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length())
            return false;
        int[] countOf = new int[26];
        for (int i = 0; i < s.length(); i++) {
            countOf[s.charAt(i) - 'a']++;
            countOf[t.charAt(i) - 'a']--;
        }
        for (int i = 0; i < countOf.length; i++)
            if (countOf[i] != 0)
                return false;
        return true;
    }
}