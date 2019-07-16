class Solution {
    public static int[] diStringMatch(String s) {
        int n = s.length();
        int[] ans = new int[n + 1];
        int min = 0, max = n;
        if (s.charAt(0) == 'I') 
            ans[0] = min++;
        else 
            ans[0] = max--;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == 'I') {
                ans[i] = min++;
            } else {
                ans[i] = max--;
            }
        }
        ans[n] = min == n ? max : min;
        return ans;
    }
}