class Solution {
    public int bestTeamScore(
        final int[] scores,
        final int[] ages
    ) {
        Integer[] sortedIndices = new Integer[scores.length];
        for (int i = 0; i < sortedIndices.length; i++) {
            sortedIndices[i] = i;
        }
        Arrays.sort(sortedIndices, (idx1, idx2) -> {
            final int age1 = ages[idx1];
            final int age2 = ages[idx2];
            if (age1 < age2)
                return -1;
            else if (age1 > age2)
                return 1;
            else {
                final int score1 = scores[idx1];
                final int score2 = scores[idx2];
                return Integer.compare(score1, score2);
            }
        });
        final int[][] memtable = new int[scores.length][scores.length + 1];
        for (int i = 0; i < memtable.length; i++) {
            Arrays.fill(memtable[i], -1);
        }
        final int res = bestTeamScore(scores, ages, sortedIndices, -1, 0, memtable);
        return res;
    }

    private int bestTeamScore(
        final int[] scores,
        final int[] ages,
        final Integer[] sortedIndices,
        final int minPlayerIdx,
        final int firstPlayerIdx,
        final int[][] memtable
    ) {
        if (firstPlayerIdx == scores.length)
            return 0;
        int score = memtable[firstPlayerIdx][minPlayerIdx + 1];
        if (score != -1)
            return score;
        final Integer playerIdx = sortedIndices[firstPlayerIdx];
        final int playerScore = scores[playerIdx];
        final int playerAge = ages[playerIdx];
        final int minAge = minPlayerIdx == -1 ? 0 : ages[minPlayerIdx];
        final int minScore = minPlayerIdx == -1 ? 0 : scores[minPlayerIdx];
        if (playerAge == minAge) {
            final int scoreIfTake = playerScore + bestTeamScore(scores, ages, sortedIndices, playerIdx, firstPlayerIdx + 1, memtable);
            final int scoreIfNotTake = bestTeamScore(scores, ages, sortedIndices, minPlayerIdx, firstPlayerIdx + 1, memtable);
            score = Math.max(scoreIfTake, scoreIfNotTake);
        } else {
            if (minScore > playerScore) {
                score = bestTeamScore(scores, ages, sortedIndices, minPlayerIdx, firstPlayerIdx + 1, memtable);
            } else {
                final int scoreIfTake = playerScore + bestTeamScore(scores, ages, sortedIndices, playerIdx, firstPlayerIdx + 1, memtable);
                final int scoreIfNotTake = bestTeamScore(scores, ages, sortedIndices, minPlayerIdx, firstPlayerIdx + 1, memtable);
                score = Math.max(scoreIfTake, scoreIfNotTake);
            }
        }
        memtable[firstPlayerIdx][minPlayerIdx + 1] = score;
        return score;
    }
}