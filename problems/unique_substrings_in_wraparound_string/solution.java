class Solution {
    public int findSubstringInWraproundString(final String p) {
        final int[] longestEndingWith = new int[26];
        int prev = p.charAt(0);
        longestEndingWith[prev - 'a'] = 1;
        int len = 1;
        for (int i = 1; i < p.length(); i++) {
            final int curr = p.charAt(i);
            int currLongest = longestEndingWith[curr - 'a'];
            if (
                (prev == 'z' && curr == 'a') ||
                prev + 1 == curr
            ) {
                len++;
                longestEndingWith[curr - 'a'] = Math.max(currLongest, len);
            } else {
                len = 1;
                longestEndingWith[curr - 'a'] = Math.max(
                    1,
                    currLongest
                );
            }
            prev = curr;
        }
        int res = 0;
        for (int i = 0; i < longestEndingWith.length; i++) {
            res += longestEndingWith[i];
        }
        return res;
    }
}