class Solution {
    public int minDifficulty(final int[] jobDifficulty, final int numDays) {
        if (jobDifficulty.length < numDays)
            return -1;
        final int[][] memtable = new int[numDays][jobDifficulty.length];
        for (int i = 0; i < memtable.length; i++) {
            Arrays.fill(memtable[i], -1);
        }
        return schedule(jobDifficulty, numDays, jobDifficulty.length - 1, memtable);
    }

    private int schedule(
        final int[] jobDifficulty,
        final int numDays,
        final int lastUnscheduledJobIdx,
        final int[][] memtable
    ) {
        int minScheduleDifficulty = memtable[numDays - 1][lastUnscheduledJobIdx];
        if (minScheduleDifficulty != -1)
            return minScheduleDifficulty;
        minScheduleDifficulty = Integer.MAX_VALUE;
        int maxJobDifficulty = -1;
        if (numDays == 1) {
            for (int unscheduledJobIdx = lastUnscheduledJobIdx; unscheduledJobIdx >= 0; unscheduledJobIdx--) {
                maxJobDifficulty = Math.max(maxJobDifficulty, jobDifficulty[unscheduledJobIdx]);
            }
            minScheduleDifficulty = maxJobDifficulty;
        } else {
            for (int unscheduledJobIdx = lastUnscheduledJobIdx; unscheduledJobIdx >= numDays - 1; unscheduledJobIdx--) {
                maxJobDifficulty = Math.max(maxJobDifficulty, jobDifficulty[unscheduledJobIdx]);
                final int remainedSchedule = schedule(jobDifficulty, numDays - 1, unscheduledJobIdx - 1, memtable);
                minScheduleDifficulty = Math.min(minScheduleDifficulty, maxJobDifficulty + remainedSchedule);
            }
        }
        memtable[numDays - 1][lastUnscheduledJobIdx] = minScheduleDifficulty;
        return minScheduleDifficulty;
    }
}