class Solution {
    public boolean isPathCrossing(final String path) {
        final Set<Point> visited = new HashSet<>();
        int x = 0;
        int y = 0;
        visited.add(
            new Point(
                x,
                y
            )
        );
        for (int i = 0; i < path.length(); i++) {
            final char c = path.charAt(i);
            switch (c) {
                case 'N' -> y++;
                case 'E' -> x++;
                case 'S' -> y--;
                case 'W' -> x--;
            }
            final Point next = new Point(
                x,
                y
            );
            if (!visited.add(next)) {
                return true;
            }
        }
        return false;
    }
    
    private static class Point {

        final int x;
        final int y;

        private Point(
            final int x,
            final int y
        ) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            final Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }

    }
}