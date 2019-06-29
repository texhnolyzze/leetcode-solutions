class Solution {
    private static final Map<String, Integer> ROMAN_TO_INT = new HashMap<>(); static {
        ROMAN_TO_INT.put("I", 1);
        ROMAN_TO_INT.put("V", 5);
        ROMAN_TO_INT.put("X", 10);
        ROMAN_TO_INT.put("L", 50);
        ROMAN_TO_INT.put("C", 100);
        ROMAN_TO_INT.put("D", 500);
        ROMAN_TO_INT.put("M", 1000);
        ROMAN_TO_INT.put("IV", 4);
        ROMAN_TO_INT.put("IX", 9);
        ROMAN_TO_INT.put("XL", 40);
        ROMAN_TO_INT.put("XC", 90);
        ROMAN_TO_INT.put("CD", 400);
        ROMAN_TO_INT.put("CM", 900);        
    }
    
    public int romanToInt(String s) {
        String[] pre = preProcessing(s);
        int res = 0;
        for (int i = 0; i < pre.length; i++) {
            res += ROMAN_TO_INT.get(pre[i]);
        }
        return res;
    }
    
    public static String[] preProcessing(String s) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < s.length() - 1) {
            String currCharAsStr = Character.toString(s.charAt(i));
            String nextCharAsStr = Character.toString(s.charAt(i + 1));
            if (ROMAN_TO_INT.get(currCharAsStr) < ROMAN_TO_INT.get(nextCharAsStr)) {
                sb.append(currCharAsStr).append(nextCharAsStr).append("|");
                i += 2;
            } else {
                sb.append(currCharAsStr).append("|");
                i++;
            }
        }
        if (i < s.length())
            sb.append(s.charAt(i));
        return sb.toString().split("\\|");
    }
}