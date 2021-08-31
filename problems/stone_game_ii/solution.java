class Solution {
    private final Score zeroScore = new Score(0, 0);

    public int stoneGameII(int[] piles) {
        final Score[][][] memtable = new Score[2][piles.length / 2 + 1][piles.length];
        return score(piles, 0, 1, true, memtable).aliceScore;
    }

    private Score score(
        final int[] piles,
        final int idx,
        final int m,
        final boolean aliceTurns,
        final Score[][][] memtable
    ) {
        if (idx >= piles.length) {
            return zeroScore;
        }
        Score res = memtable[aliceTurns ? 1 : 0][m - 1][idx];
        if (res != null) {
            return res;
        }
        res = new Score(0, 0);
        int prefixScore = 0;
        final int bound = Math.min(piles.length, idx + 2 * m);
        if (aliceTurns) {
            for (int i = idx; i < bound; i++) {
                prefixScore += piles[i];
                final Score sub = score(piles, i + 1, Math.max(m, i - idx + 1), false, memtable);
                if (res.aliceScore < sub.aliceScore + prefixScore) {
                    res.aliceScore = sub.aliceScore + prefixScore;
                    res.bobScore = sub.bobScore;
                }
            }
        } else {
            for (int i = idx; i < bound; i++) {
                prefixScore += piles[i];
                final Score sub = score(piles, i + 1, Math.max(m, i - idx + 1), true, memtable);
                if (res.bobScore < sub.bobScore + prefixScore) {
                    res.bobScore = sub.bobScore + prefixScore;
                    res.aliceScore = sub.aliceScore;
                }
            }
        }
        memtable[aliceTurns ? 1 : 0][m - 1][idx] = res;
        return res;
    }

    private static final class Score {
        int aliceScore;
        int bobScore;
        private Score(
            int aliceScore,
            int bobScore
        ) {
            this.aliceScore = aliceScore;
            this.bobScore = bobScore;
        }
    }
}