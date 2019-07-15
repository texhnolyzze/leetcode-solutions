class Solution {
    public boolean detectCapitalUse(String word) {
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (Character.isUpperCase(c)) {
                if (i != 0 && Character.isLowerCase(word.charAt(i - 1)))
                    return false;
            } else {
                if (i > 1 && Character.isUpperCase(word.charAt(i - 1)))
                    return false;
            }
        }
        return true;
    }
}