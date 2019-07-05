class Solution {
    public boolean isIsomorphic(String s, String t) {
        Set<Character> added = new HashSet<>();
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            Character corresponding = map.get(c);
            if (corresponding == null) {
                if (added.contains(t.charAt(i)))
                    return false;
                map.put(c, t.charAt(i));
                added.add(t.charAt(i));
            } else {
                if (corresponding != t.charAt(i))
                    return false;
            }
        }
        return true;
    }
}