class Solution {
    public int[] frequencySort(final int[] nums) {
        final int[] freq = new int[201];
        for (final int num : nums) {
            freq[num + 100]++;
        }
        final PriorityQueue<Integer> queue = new PriorityQueue<>(
            (n1, n2) -> {
                final int cmp = Integer.compare(freq[n1 + 100], freq[n2 + 100]);
                if (cmp == 0) {
                    return -Integer.compare(n1, n2);
                }
                return cmp;
            }
        );
        for (int n = -100; n < 101; n++) {
            if (freq[n + 100] > 0) {
                queue.add(n);
            }
        }
        int pos = 0;
        final int[] res = new int[nums.length];
        while (!queue.isEmpty()) {
            final int next = queue.poll();
            final int fq = freq[next + 100];
            for (int i = 0; i < fq; i++) {
                res[pos++] = next;
            }
        }
        return res;
    }
}