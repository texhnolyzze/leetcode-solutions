class Solution {

    private static final double HALF_PI = Math.PI / 2;

    private enum Quadrant {

        UPPER_RIGHT(0.0),
        BOTTOM_RIGHT(Math.PI + HALF_PI),
        BOTTOM_LEFT(Math.PI),
        UPPER_LEFT(HALF_PI);

        private final double radianStart;

        Quadrant(final double radianStart) {
            this.radianStart = radianStart;
        }

        double randomAngle() {
            return radianStart + ThreadLocalRandom.current().nextDouble() * HALF_PI;
        }

        Quadrant roundRobinNext() {
            return switch (this) {
                case UPPER_RIGHT -> BOTTOM_RIGHT;
                case BOTTOM_RIGHT -> BOTTOM_LEFT;
                case BOTTOM_LEFT -> UPPER_LEFT;
                case UPPER_LEFT -> UPPER_RIGHT;
            };
        }

    }

    private static final int GRANULARITY = 30000;

    private final double radius;
    private final double radiusSquare;
    private final double centerX;
    private final double centerY;

    private final Set<Point> points = new HashSet<>();
    private Quadrant nextQuadrant = Quadrant.UPPER_RIGHT;

    public Solution(double radius, double centerX, double centerY) {
        this.radius = radius;
        this.radiusSquare = radius * radius;
        this.centerX = centerX;
        this.centerY = centerY;
    }

    public double[] randPoint() {
        Point point;
        do {
            point = new Point(randPoint0(nextQuadrant));
        } while (points.contains(point) || point.squareDistanceTo(centerX, centerY) > radiusSquare);
        points.add(point);
        nextQuadrant = nextQuadrant.roundRobinNext();
        return point.coords;
    }

    private double[] randPoint0(Quadrant quadrant) {
        double angle = quadrant.randomAngle();
        double len = Math.sqrt(rand()) * radius;
        return new double[] {
            centerX + len * Math.cos(angle),
            centerY + len * Math.sin(angle)
        };
    }

    private double rand() {
        double i = ThreadLocalRandom.current().nextInt(GRANULARITY + 1);
        return (GRANULARITY - i) / GRANULARITY;
    }

    private static class Point {

        final double[] coords;

        private Point(final double[] coords) {
            this.coords = coords;
        }

        private double squareDistanceTo(final double x, final double y) {
            final double dx = coords[0] - x;
            final double dy = coords[1] - y;
            return dx * dx + dy * dy;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            final Point point = (Point) o;
            if (Double.compare(coords[0], point.coords[0]) != 0) return false;
            return Double.compare(coords[1], point.coords[1]) == 0;
        }

        @Override
        public int hashCode() {
            int result;
            long temp;
            temp = Double.doubleToLongBits(coords[0]);
            result = (int) (temp ^ (temp >>> 32));
            temp = Double.doubleToLongBits(coords[1]);
            result = 31 * result + (int) (temp ^ (temp >>> 32));
            return result;
        }

    }
    
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(radius, x_center, y_center);
 * double[] param_1 = obj.randPoint();
 */