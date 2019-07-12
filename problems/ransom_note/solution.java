class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        char[] countOf = new char[27];
        for (int i = 0; i < magazine.length(); i++)
            countOf[magazine.charAt(i) - 'a']++;
        for (int i = 0; i < ransomNote.length(); i++) {
            if (countOf[ransomNote.charAt(i) - 'a'] == 0)
                return false;
            countOf[ransomNote.charAt(i) - 'a']--;
        }
        return true;
    }
}