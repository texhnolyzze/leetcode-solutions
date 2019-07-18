class Solution {
    public static int lengthOfLongestSubstring(String s) {
        if (s.length() == 0)
            return 0;
        Map<Character, Integer> lastIndexOf = new HashMap<>();
//        int[] lastIndexOf = new int[128];
//        Arrays.fill(lastIndexOf, -1);
        int longestLen = 1;
        for (int i = 0; i < s.length(); i++) {
            lastIndexOf.put(s.charAt(i), i);
//            lastIndexOf[s.charAt(i) - 'a'] = i;
            int len = 1;
            int max = i;
            int nextMax = nextMax(lastIndexOf, max);
            while (nextMax != -1 && max - nextMax == 1) {
                longestLen = Math.max(longestLen, ++len);
                max = nextMax;
                nextMax = nextMax(lastIndexOf, max);
            }
        }
        return longestLen;
    }
    
    private static int nextMax(Map<Character, Integer> lastIndexOf, int max) {
        int nextMax = -1;
        for (int lastIndex : lastIndexOf.values()) 
            if (lastIndex < max)
                nextMax = Math.max(nextMax, lastIndex);
        return nextMax;
    }
}