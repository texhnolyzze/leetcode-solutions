class Solution {
    private final int x = 0;
    private final int y = 1;

    public int maxPoints(int[][] points) {
        if (points.length <= 2) {
            return points.length;
        }
        final Map<Line, BitSet> count = new HashMap<>();
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                final int x1 = points[i][x];
                final int y1 = points[i][y];
                final int x2 = points[j][x];
                final int y2 = points[j][y];
                final BitSet line = count.computeIfAbsent(
                    new Line(x1, y1, x2, y2),
                    unused -> new BitSet(points.length)
                );
                line.set(i);
                line.set(j);
            }
        }
        return count.values().stream().mapToInt(BitSet::cardinality).max().orElseThrow();
    }

    private static class Line {

        final int dx;
        final int dy;
        final double b;
        final boolean vertical;

        private Line(
            final int x1,
            final int y1,
            final int x2,
            final int y2
        ) {
            final int gcd = gcd(x2 - x1, y2 - y1);
            this.dx = (x2 - x1) / gcd;
            this.dy = (y2 - y1) / gcd;
            if (this.dx == 0) {
                this.vertical = true;
                this.b = x1;
            } else {
                this.vertical = false;
                this.b = y1 - ((double) this.dy * x1) / dx;
            }
        }

        private int gcd(final int a, final int b) {
            if (b == 0) {
                return a;
            }
            return gcd(b, a % b);
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            final Line line = (Line) o;
            if (dx != line.dx) return false;
            if (dy != line.dy) return false;
            if (Double.compare(line.b, b) != 0) return false;
            return vertical == line.vertical;
        }

        @Override
        public int hashCode() {
            int result;
            long temp;
            result = dx;
            result = 31 * result + dy;
            temp = Double.doubleToLongBits(b);
            result = 31 * result + (int) (temp ^ (temp >>> 32));
            result = 31 * result + (vertical ? 1 : 0);
            return result;
        }

    }
}