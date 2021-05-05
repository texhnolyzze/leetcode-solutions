class Solution {
    public int minimumJumps(
        int[] forbidden,
        int forward,
        int backward,
        int target
    ) {
        Arrays.sort(forbidden);
        int max = Math.max(forbidden[forbidden.length - 1], target) + 2 * forward + backward;
        Queue<PositionMeta> queue = new LinkedList<>();
        Set<PositionMeta> visited = new HashSet<>();
        PositionMeta start = new PositionMeta(0, 0, false);
        queue.add(start);
        visited.add(start);
        while (!queue.isEmpty()) {
            PositionMeta current = queue.poll();
            if (current.position == target) {
                return current.jumps;
            }
            PositionMeta forwardPos = new PositionMeta(current.position + forward, current.jumps + 1, false);
            PositionMeta backwardPos = new PositionMeta(current.position - backward, current.jumps + 1, true);
            if (
                Arrays.binarySearch(forbidden, forwardPos.position) < 0 &&
                !visited.contains(forwardPos) &&
                (
                    (forward >= backward && forwardPos.position - backward <= target) ||
                    (forward < backward && forwardPos.position <= max)
                )
            ) {
                visited.add(forwardPos);
                queue.add(forwardPos);
            }
            if (
                backwardPos.position > 0 &&
                Arrays.binarySearch(forbidden, backwardPos.position) < 0 &&
                !visited.contains(backwardPos) &&
                !current.jumpedHereBackward
            ) {
                visited.add(backwardPos);
                queue.add(backwardPos);
            }
        }
        return -1;
    }

    private static class PositionMeta {

        final int position;
        final int jumps;
        final boolean jumpedHereBackward;

        private PositionMeta(
            final int position,
            final int jumps,
            final boolean jumpedHereBackward
        ) {
            this.position = position;
            this.jumps = jumps;
            this.jumpedHereBackward = jumpedHereBackward;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (!(o instanceof PositionMeta)) return false;
            final PositionMeta other = (PositionMeta) o;
            return position == other.position && jumpedHereBackward == other.jumpedHereBackward;
        }

        @Override
        public int hashCode() {
            int result = position;
            result = 31 * result + (jumpedHereBackward ? 1 : 0);
            return result;
        }

    }
}