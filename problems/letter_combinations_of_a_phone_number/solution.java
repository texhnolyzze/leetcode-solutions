class Solution {
    private static final Map<Character, char[]> KEYBOARD = new HashMap<>();
    static {
        KEYBOARD.put('2', new char[] {'a', 'b', 'c'});
        KEYBOARD.put('3', new char[] {'d', 'e', 'f'});
        KEYBOARD.put('4', new char[] {'g', 'h', 'i'});
        KEYBOARD.put('5', new char[] {'j', 'k', 'l'});
        KEYBOARD.put('6', new char[] {'m', 'n', 'o'});
        KEYBOARD.put('7', new char[] {'p', 'q', 'r', 's'});
        KEYBOARD.put('8', new char[] {'t', 'u', 'v'});
        KEYBOARD.put('9', new char[] {'w', 'x', 'y', 'z'});
    }
    
    public static List<String> letterCombinations(String digits) {
        if (digits.isEmpty())
            return Collections.EMPTY_LIST;
        int n = KEYBOARD.get(digits.charAt(0)).length;
        for (int i = 1; i < digits.length(); i++)
            n *= KEYBOARD.get(digits.charAt(i)).length;
        List<String> ans = new ArrayList<>(n);
        backtrack(digits, 0, new StringBuilder(), ans);
        return ans;
    }

    private static void backtrack(String digits, int depth, StringBuilder sb, List<String> ans) {
        if (depth == digits.length()) {
            ans.add(sb.toString());
            return;
        }
        char digit = digits.charAt(depth);
        for (char c : KEYBOARD.get(digit)) {
            sb.append(c);
            backtrack(digits, depth + 1, sb, ans);
            sb.setLength(depth);
        }
    }
}