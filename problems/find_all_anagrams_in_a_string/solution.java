class Solution {
    public static List<Integer> findAnagrams(String s, String p) {
        if (p.length() > s.length())
            return Collections.EMPTY_LIST;
        List<Integer> output = new ArrayList<>();
        Map<Character, Integer> patternSlidingMap   = new HashMap<>(), 
                                patternCountOf      = new HashMap<>(); // final
        for (int i = 0; i < p.length(); i++) 
            patternCountOf.put(p.charAt(i), patternSlidingMap.merge(p.charAt(i), 1, Integer::sum));
        Map<Character, Integer> sWindowMap = new HashMap<>();
        int numMatches = 0;
        for (int i = 0; i < p.length(); i++) {
            char c = s.charAt(i);
            sWindowMap.merge(c, 1, Integer::sum);
            Integer cCount = patternSlidingMap.get(c);
            if (cCount != null && cCount != 0) {
                patternSlidingMap.put(c, cCount - 1);
                numMatches++;
            }
        }
        if (numMatches == p.length()) 
            output.add(0);
        for (int i = p.length(); i < s.length(); i++) {
            char prevWindowChar = s.charAt(i - p.length());
            sWindowMap.merge(prevWindowChar, -1, Integer::sum);
            Integer reservedForPrevChar = patternSlidingMap.get(prevWindowChar);
            if (reservedForPrevChar != null) {
                if (sWindowMap.get(prevWindowChar) < patternCountOf.get(prevWindowChar)) {
                    numMatches--;
                    patternSlidingMap.put(prevWindowChar, reservedForPrevChar + 1);
                }
            }
            char newChar = s.charAt(i);
            sWindowMap.merge(newChar, 1, Integer::sum);
            Integer reservedForNewChar = patternSlidingMap.get(newChar);
            if (reservedForNewChar != null) {
                if (reservedForNewChar != 0) {
                    patternSlidingMap.put(newChar, reservedForNewChar - 1);
                    numMatches++;
                }
            }
            if (numMatches == p.length())
                output.add(i - p.length() + 1);
        }
        return output;
    }
}