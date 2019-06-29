class Solution {
    public boolean isValid(String s) {
        LinkedList<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '(':
                case '[':
                case '{':
                    stack.addLast(c);
                    break;
                default:
                    if (stack.isEmpty())
                        return false;
                    char prevExpected = '\0';
                    switch (c) {
                        case ')': prevExpected = '('; break;
                        case ']': prevExpected = '['; break;
                        case '}': prevExpected = '{'; break;
                    }
                    if (prevExpected != stack.pollLast())
                        return false;
            }
        }
        return stack.isEmpty();
    }
}