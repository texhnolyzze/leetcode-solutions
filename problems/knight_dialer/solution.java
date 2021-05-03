class Solution {
    private static final int[][] TRANSITIONS = {
        {4, 6},
        {6, 8},
        {7, 9},
        {4, 8},
        {0, 3, 9},
        {},
        {0, 1, 7},
        {2, 6},
        {1, 3},
        {2, 4}
    };

    public int knightDialer(int n) {
        int sum = 0;
        for (int i : knightDialer0(n)) {
            sum = (sum + i) % 1000000007;
        }
        return sum;
    }

    private int[] knightDialer0(int n) {
        int[] res = new int[10];
        if (n == 1) {
            for (int i = 0; i < 10; i++) {
                res[i] = 1;
            }
        } else {
            int[] prev = knightDialer0(n - 1);
            for (int i = 0; i < 10; i++) {
                int numEndsWithI = prev[i];
                int[] transitions = TRANSITIONS[i];
                for (int transition : transitions) {
                    res[transition] = (res[transition] + numEndsWithI) % 1000000007;
                }
            }
        }
        return res;
    }
}