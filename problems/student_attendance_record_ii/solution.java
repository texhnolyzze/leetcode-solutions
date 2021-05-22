class Solution {
    private static final int MOD = 1000000007;

    public int checkRecord(final int n) {
        final int[][][] memtable = new int[n + 1][2][3];
        memtable[1][absent(false)][0] = 1;
        memtable[1][absent(false)][1] = 1;
        memtable[1][absent(true)][0] = 1;
        for (int recordLength = 2; recordLength <= n; recordLength++) {
            memtable[recordLength][absent(true)][0] =
                (
                    (
                        (
                            (
                                (
                                    memtable[recordLength - 1][absent(false)][0] +
                                    memtable[recordLength - 1][absent(false)][1]
                                ) % MOD +
                                memtable[recordLength - 1][absent(false)][2]
                            ) % MOD +
                            memtable[recordLength - 1][absent(true)][0]
                        ) % MOD +
                        memtable[recordLength - 1][absent(true)][1]
                    ) % MOD +
                    memtable[recordLength - 1][absent(true)][2]
                ) % MOD;
            memtable[recordLength][absent(true)][1] = memtable[recordLength - 1][absent(true)][0];
            memtable[recordLength][absent(true)][2] = memtable[recordLength - 1][absent(true)][1];
            memtable[recordLength][absent(false)][0] =
                (
                    (
                        memtable[recordLength - 1][absent(false)][0] +
                        memtable[recordLength - 1][absent(false)][1]
                    ) % MOD +
                    memtable[recordLength - 1][absent(false)][2]
                ) % MOD;
            memtable[recordLength][absent(false)][1] = memtable[recordLength - 1][absent(false)][0];
            memtable[recordLength][absent(false)][2] = memtable[recordLength - 1][absent(false)][1];
        }
        return
            (
                (
                    (
                        (
                            (
                                memtable[n][absent(false)][0] +
                                memtable[n][absent(false)][1]
                            ) % MOD +
                            memtable[n][absent(false)][2]
                        ) % MOD +
                        memtable[n][absent(true)][0]
                    ) % MOD +
                    memtable[n][absent(true)][1]
                ) % MOD +
                memtable[n][absent(true)][2]
            ) % MOD;
    }

    private int absent(boolean absent) {
        return absent ? 1 : 0;
    }
}