class Solution {
    public static boolean isLongPressedName(String name, String typed) {
        if (typed.length() < name.length())
            return false;
        for (int i = 0, j = 0;;) {
            int count = 1;
            while (i < name.length() - 1 && name.charAt(i) == name.charAt(i + 1)) {
                count++;
                i++;
            }
            while (j < typed.length() && typed.charAt(j) == name.charAt(i)) {
                count--;
                j++;
            }
            if (count > 0)
                return false;
            i++;
            if (i == name.length()) 
                return j == typed.length();
        }
    }
}