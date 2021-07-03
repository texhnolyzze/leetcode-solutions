class Solution {
    public int superEggDrop(final int k, final int n) {
        final int[][] memtable = new int[150][150];
        for (int i = 1; i <= 149; i++) {
            for (int j = 1; j <= 149; j++) {
                if (j >= i) {
                    memtable[i][j] = 1 << (i - 1);
                } else {
                    memtable[i][j] = memtable[i - 1][j] + memtable[i - 1][j - 1];
                }
            }
        }
        int kVar = 1;
        int floor = 0;
        while (true) {
            floor += memtable[kVar][k];
            if (floor >= n) {
                return kVar;
            }
            kVar++;
        }
    }
}