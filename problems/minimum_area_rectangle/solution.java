class Solution {
    public int minAreaRect(int[][] points) {
        Point tempPoint = new Point(new int[2]);
        Set<Point> pointSet = new HashSet<>();
        for (int[] point : points)
            pointSet.add(new Point(point));
        int minArea = Integer.MAX_VALUE;
        for (int i = 1; i < points.length; i++) {
            int[] pi = points[i];
            for (int j = i - 1; j >= 0; j--) {
                int[] pj = points[j];
                int dx = pi[0] - pj[0];
                int dy = pi[1] - pj[1];
                int area = Math.abs(dx * dy);
                if (area > 0 && area < minArea) {
                    tempPoint.point[0] = pj[0] + dx;
                    tempPoint.point[1] = pj[1];
                    if (!pointSet.contains(tempPoint))
                        continue;
                    tempPoint.point[0] = pj[0];
                    tempPoint.point[1] = pj[1] + dy;
                    if (!pointSet.contains(tempPoint))
                        continue;
                    minArea = area;
                }
            }
        }
        return minArea == Integer.MAX_VALUE ? 0 : minArea;
    }
    
    private static class Point {
        
        int[] point;
        Point() {}
        Point(int[] point) {
            this.point = point;
        }

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 79 * hash + this.point[0];
            hash = 79 * hash + this.point[1];
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            final Point other = (Point) obj;
            if (this.point[0] != other.point[0]) return false;
            return this.point[1] == other.point[1];
        }
        
    }
}