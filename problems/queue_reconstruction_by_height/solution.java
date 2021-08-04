class Solution {
    public int[][] reconstructQueue(final int[][] people) {
        final int heightIdx = 0;
        final int kIdx = 1;
        Arrays.sort(people, (p1, p2) -> {
            final int cmp = Integer.compare(p1[heightIdx], p2[heightIdx]);
            if (cmp == 0) {
                return -Integer.compare(p1[kIdx], p2[kIdx]);
            }
            return cmp;
        });
        final List<int[]> queue = new LinkedList<>();
        for (int i = people.length - 1; i >= 0; i--) {
            final int[] person = people[i];
            queue.add(person[kIdx], person);
        }
        int i = 0;
        for (final int[] person : queue) {
            people[i++] = person;
        }
        return people;
    }
}