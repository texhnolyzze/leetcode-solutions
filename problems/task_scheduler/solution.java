class Solution {
    public int leastInterval(final char[] tasks, final int n) {
        final int[] count = new int[26];
        final int[] finished = new int[26];
        Arrays.fill(finished, -100);
        for (final char task : tasks) {
            count[task - 'A']++;
        }
        final PriorityQueue<Character> queue = new PriorityQueue<>(
            (c1, c2) -> {
                final int cmp = -Integer.compare(count[c1 - 'A'], count[c2 - 'A']);
                if (cmp == 0) {
                    return Integer.compare(finished[c1 - 'A'], finished[c2 - 'A']);
                }
                return cmp;
            }
        );
        for (int i = 0; i < 26; i++) {
            if (count[i] != 0) {
                queue.add((char) ('A' + i));
            }
        }
        int time = 0;
        int lastRoundStart = 0;
        int done = 0;
        final int total = tasks.length;
        while (true) {
            final Character next = queue.poll();
            final int nextIdx = next - 'A';
            final int diff = time - finished[nextIdx];
            if (diff < n) {
                time = time + 1 + (n - diff);
            } else {
                time++;
            }
            finished[nextIdx] = time;
            count[nextIdx]--;
            done++;
            if (done == total) {
                break;
            }
            if (queue.isEmpty() || time - lastRoundStart >= n + 1) {
                for (int i = 0; i < 26; i++) {
                    final char c = (char) (i + 'A');
                    if (count[i] != 0 && !queue.contains(c)) {
                        queue.add(c);
                    }
                }
                lastRoundStart = time;
            }
        }
        return time;
    }
}