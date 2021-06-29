class Solution {
    public boolean stoneGame(final int[] piles) {
        final Score[][][] memtable = new Score[piles.length][piles.length][2];
        final Score score = stoneGame(piles, 0, piles.length - 1, true, memtable);
        return score.alexScore > score.leeScore;
    }

    private Score stoneGame(
        final int[] piles,
        final int left,
        final int right,
        final boolean alexMoves,
        final Score[][][] memtable
    ) {
        if (left > right) {
            return new Score(
                0,
                0
            );
        } else if (left == right) {
            return alexMoves ? new Score(piles[left], 0) : new Score(0, piles[left]);
        } else {
            Score res = memtable[left][right][alexMoves ? 1 : 0];
            if (res != null) {
                return res;
            }
            final Score scoreLeft = stoneGame(piles, left + 1, right, !alexMoves, memtable);
            final Score scoreRight = stoneGame(piles, left, right - 1, !alexMoves, memtable);
            if (alexMoves) {
                final int leftDiff = scoreLeft.alexScore + piles[left] - scoreLeft.leeScore;
                final int rightDiff = scoreRight.alexScore + piles[right] - scoreRight.leeScore;
                if (leftDiff > rightDiff) {
                    res = new Score(scoreLeft.alexScore + piles[left], scoreLeft.leeScore);
                } else {
                    res = new Score(scoreRight.alexScore + piles[right], scoreRight.leeScore);
                }
            } else {
                final int leftDiff = scoreLeft.leeScore + piles[left] - scoreLeft.alexScore;
                final int rightDiff = scoreRight.leeScore + piles[right] - scoreRight.alexScore;
                if (leftDiff > rightDiff) {
                    res = new Score(scoreLeft.alexScore, scoreLeft.leeScore + piles[left]);
                } else {
                    res = new Score(scoreRight.alexScore, scoreRight.leeScore + piles[right]);
                }
            }
            memtable[left][right][alexMoves ? 1 : 0] = res;
            return res;
        }
    }

    private static class Score {
        final int alexScore;
        final int leeScore;
        Score(
            final int alexScore,
            final int leeScore
        ) {
            this.alexScore = alexScore;
            this.leeScore = leeScore;
        }
    }
}