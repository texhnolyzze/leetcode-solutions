class Solution {
    public boolean canCross(final int[] stones) {
        final Set<JumpPosition> visited = new HashSet<>();
        final Queue<JumpPosition> queue = new LinkedList<>();
        final JumpPosition start = new JumpPosition(0, 0, 0);
        visited.add(start);
        queue.add(start);
        while (!queue.isEmpty()) {
            final JumpPosition pos = queue.poll();
            if (pos.idx == stones.length - 1) {
                return true;
            }
            if (pos.k - 1 > 0) {
                final int nextStone = pos.stone + pos.k - 1;
                final int idx = Arrays.binarySearch(stones, nextStone);
                if (idx > pos.idx) {
                    final JumpPosition next = new JumpPosition(
                        idx,
                        nextStone,
                        pos.k - 1
                    );
                    if (!visited.contains(next)) {
                        visited.add(next);
                        queue.add(next);
                    }
                }
            }
            if (pos.k > 0) {
                final int nextStone = pos.stone + pos.k;
                final int idx = Arrays.binarySearch(stones, nextStone);
                if (idx > pos.idx) {
                    final JumpPosition next = new JumpPosition(
                        idx,
                        nextStone,
                        pos.k
                    );
                    if (!visited.contains(next)) {
                        visited.add(next);
                        queue.add(next);
                    }
                }
            }
            {
                final int nextStone = pos.stone + pos.k + 1;
                final int idx = Arrays.binarySearch(stones, nextStone);
                if (idx > pos.idx) {
                    final JumpPosition next = new JumpPosition(
                        idx,
                        nextStone,
                        pos.k + 1
                    );
                    if (!visited.contains(next)) {
                        visited.add(next);
                        queue.add(next);
                    }
                }
            }
        }
        return false;
    }

    private static class JumpPosition {

        final int idx;
        final int stone;
        final int k;

        private JumpPosition(final int idx, final int stone, final int k) {
            this.idx = idx;
            this.stone = stone;
            this.k = k;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            final JumpPosition that = (JumpPosition) o;
            if (stone != that.stone) return false;
            return k == that.k;
        }

        @Override
        public int hashCode() {
            int result = stone;
            result = 31 * result + k;
            return result;
        }

        @Override
        public String toString() {
            return "JumpPosition{" +
                "idx=" + idx +
                ", stone=" + stone +
                ", k=" + k +
                '}';
        }

    }
}