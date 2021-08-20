class Solution {
    public int minSetSize(final int[] arr) {
        final Map<Integer, Integer> countOf = new HashMap<>(arr.length);
        for (final int n : arr) {
            countOf.compute(
                n,
                (unused, count) -> count == null ? 1 : count + 1
            );
        }
        final PriorityQueue<Integer> queue = new PriorityQueue<>(
            (n1, n2) -> -Integer.compare(countOf.get(n1), countOf.get(n2))
        );
        queue.addAll(countOf.keySet());
        final int total = arr.length;
        int res = 0;
        int removed = 0;
        while (removed < total / 2) {
            final Integer n = queue.poll();
            removed += countOf.get(n);
            res++;
        }
        return res;
    }
}