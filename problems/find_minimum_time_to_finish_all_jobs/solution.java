class Solution {
    private int result;

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public int minimumTimeRequired(int[] jobs, int numWorkers) {
        if (numWorkers == jobs.length)
            return Arrays.stream(jobs).max().getAsInt();
        jobs = Arrays.stream(jobs).boxed().sorted(Comparator.reverseOrder()).mapToInt(Integer::intValue).toArray();
        int[] assignedJobs = new int[jobs.length];
        Arrays.fill(assignedJobs, -1);
        final int jobsRemained = jobs.length;
        final int[] numTasksPerWorker = new int[numWorkers];
        final int[] timePerWorker = new int[numWorkers];
        this.result = greedyResult(jobs, timePerWorker);
        minimumTimeRequired(jobs, assignedJobs, numWorkers, jobsRemained, numTasksPerWorker, timePerWorker);
        return result;
    }

    private int greedyResult(final int[] jobs, final int[] timePerWorker) {
        for (final int job : jobs) {
            int mostIdleWorker = 0;
            for (int workerId = 1; workerId < timePerWorker.length; workerId++) {
                if (timePerWorker[workerId] < timePerWorker[mostIdleWorker]) {
                    mostIdleWorker = workerId;
                }
            }
            timePerWorker[mostIdleWorker] += job;
        }
        int res = maxTime(timePerWorker.length, timePerWorker);
        Arrays.fill(timePerWorker, 0);
        return res;
    }

    private void minimumTimeRequired(
        int[] jobs,
        int[] assignedJobs,
        final int numWorkers,
        final int jobsRemained,
        final int[] numTasksPerWorker,
        final int[] timePerWorker
    ) {
        if (jobsRemained == 0) {
            this.result = Math.min(result, maxTime(numWorkers, timePerWorker));
        } else {
            if (result <= maxTime(numWorkers, timePerWorker))
                return;
            for (int jobId = 0; jobId < assignedJobs.length; jobId++) {
                if (assignedJobs[jobId] == -1) {
                    Set<Integer> seenTime = new HashSet<>();
                    for (int workerId = 0; workerId < numWorkers; workerId++) {
                        if (seenTime.contains(timePerWorker[workerId]))
                            continue;
                        assignedJobs[jobId] = workerId;
                        numTasksPerWorker[workerId]++;
                        seenTime.add(timePerWorker[workerId]);
                        timePerWorker[workerId] += jobs[jobId];
                        minimumTimeRequired(
                            jobs,
                            assignedJobs,
                            numWorkers,
                            jobsRemained - 1,
                            numTasksPerWorker,
                            timePerWorker
                        );
                        timePerWorker[workerId] -= jobs[jobId];
                        numTasksPerWorker[workerId]--;
                    }
                    assignedJobs[jobId] = -1;
                    break;
                }
            }
        }
    }

    private int maxTime(final int numWorkers, final int[] timePerWorker) {
        int max = Integer.MIN_VALUE;
        for (int workerId = 0; workerId < numWorkers; workerId++) {
            max = Math.max(max, timePerWorker[workerId]);
        }
        return max;
    }
}