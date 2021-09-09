class Solution {
    public int fib(final int n) {
        if (n <= 1) {
            return n == 0 ? 0 : 1;
        }
        int prev = 1;
        int prevprev = 0;
        for (int i = 0; i < n; i++) {
            final int curr = prev + prevprev;
            prevprev = prev;
            prev = curr;
        }
        return prevprev;
    }
}