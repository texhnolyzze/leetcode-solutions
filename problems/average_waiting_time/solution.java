class Solution {
    private static final int ARRIVAL = 0;
    private static final int DURATION = 1;

    public double averageWaitingTime(int[][] jobs) {
        final Queue<int[]> queue = new LinkedList<>();
        int jobIdx = 0;
        long waitingTime = 0L;
        long currentTime = 1L;
        while (true) {
            while (jobIdx < jobs.length) {
                final int[] job = jobs[jobIdx];
                if (job[ARRIVAL] <= currentTime) {
                    queue.add(job);
                    jobIdx++;
                } else if (queue.isEmpty()) {
                    jobIdx++;
                    queue.add(job);
                    currentTime = job[ARRIVAL];
                } else
                    break;
            }
            final int[] next = queue.poll();
            if (next == null)
                break;
            currentTime += next[DURATION];
            waitingTime += (currentTime - next[ARRIVAL]);
        }
        return ((double) waitingTime) / jobs.length;
    }
}