class Solution {
    public static boolean wordPattern(String pattern, String str) {
        String[] split = str.split(" ");
        if (split.length != pattern.length())
            return false;
        Set<String> set = new HashSet<>();
        Map<Character, String> map = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            String word = map.get(c);
            if (word != null) {
                if (!word.equals(split[i]))
                    return false;
            } else {
                if (set.contains(split[i]))
                    return false;
                set.add(split[i]);
                map.put(c, split[i]);
            }
        }
        return true;
    }
}