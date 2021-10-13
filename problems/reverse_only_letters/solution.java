class Solution {
    public String reverseOnlyLetters(final String s) {
        final char[] arr = s.toCharArray();
        for (int i = 0, j = arr.length - 1;;) {
            while (i < s.length() && !Character.isLetter(s.charAt(i))) {
                i++;
            }
            while (j > 0 && !Character.isLetter(s.charAt(j))) {
                j--;
            }
            if (i == s.length() || j == 0 || i >= j) {
                break;
            }
            final char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
        return String.valueOf(arr);
    }
}