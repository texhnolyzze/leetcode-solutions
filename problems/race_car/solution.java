class Solution {
    public int racecar(int target) {
        final int max = target + target / 2;
        final int min = -max;
        PriorityQueue<TimestampedPosition> queue = new PriorityQueue<>(
            Comparator.comparingInt(
                TimestampedPosition::getTimestamp
            )
        );
        Set<TimestampedPosition> visited = new HashSet<>();
        TimestampedPosition start = new TimestampedPosition(
            0,
            1,
            0
        );
        queue.add(start);
        visited.add(start);
        while (!queue.isEmpty()) {
            TimestampedPosition position = queue.poll();
            if (position.position == target)
                return position.timestamp;
            TimestampedPosition accelerated = new TimestampedPosition(
                position.timestamp + 1,
                position.speed * 2,
                position.position + position.speed
            );
            TimestampedPosition reversed = new TimestampedPosition(
                position.timestamp + 1,
                position.speed > 0 ? -1 : 1,
                position.position
            );
            if (min <= accelerated.position && accelerated.position <= max && !visited.contains(accelerated)) {
                visited.add(accelerated);
                queue.add(accelerated);
            }
            if (!visited.contains(reversed)) {
                visited.add(reversed);
                queue.add(reversed);
            }
        }
        return -1;
    }

    private static class TimestampedPosition {

        final int timestamp;
        final int speed;
        final int position;

        private TimestampedPosition(
            final int timestamp,
            final int speed,
            final int position
        ) {
            this.timestamp = timestamp;
            this.speed = speed;
            this.position = position;
        }

        int getTimestamp() {
            return timestamp;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            final TimestampedPosition that = (TimestampedPosition) o;
            if (speed != that.speed) return false;
            return position == that.position;
        }

        @Override
        public int hashCode() {
            int result = speed;
            result = 31 * result + position;
            return result;
        }

    }
}