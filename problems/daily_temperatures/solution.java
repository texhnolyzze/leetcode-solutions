class Solution {
    public static int[] dailyTemperatures(int[] t) {
        int[] ans = new int[t.length];
        Deque<int[]> stack = new LinkedList<>();
        stack.push(new int[] {t[t.length - 1], t.length - 1});
        for (int i = t.length - 2; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek()[0] <= t[i])
                stack.pop();
            if (!stack.isEmpty()) {
                ans[i] = stack.peek()[1] - i;
            }
            stack.push(new int[] {t[i], i});
        }
        return ans;
    }
}