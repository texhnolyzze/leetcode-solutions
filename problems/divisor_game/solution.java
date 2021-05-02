class Solution {
    private static final int BOB_ID = 0;
    private static final int ALICE_ID = 1;

    public boolean divisorGame(int n) {
        int[][] memtable = new int[n + 1][2];
        for (int[] arr : memtable) {
            Arrays.fill(arr, -1);
        }
        return whoWins(memtable, ALICE_ID, n) == ALICE_ID;
    }

    private int whoWins(int[][] memtable, int whoTurnsId, int n) {
        if (n <= 1)
            return whoTurnsId == BOB_ID ? ALICE_ID : BOB_ID;
        int memo = memtable[n][whoTurnsId];
        if (memo != -1)
            return memo;
        boolean canWin = false;
        for (int i = 1; i < n; i++) {
            if (n % i == 0) {
                int whoWins = whoWins(memtable, whoTurnsId == ALICE_ID ? BOB_ID : ALICE_ID, n - i);
                if (whoWins == whoTurnsId)
                    canWin = true;
            }
        }
        int res = canWin ? whoTurnsId : whoTurnsId == ALICE_ID ? BOB_ID : ALICE_ID;
        memtable[n][whoTurnsId] = res;
        return res;
    }
}