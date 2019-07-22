class Solution {
    public static int minAddToMakeValid(String s) {
        int ans = 0;
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(')
                stack.push(c);
            else {
                if (stack.isEmpty())
                    ans++;
                else
                    stack.pop();
            }
        }
        return ans + stack.size();
    }
}