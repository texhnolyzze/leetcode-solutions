class Solution {
    public int nearestValidPoint(
        final int x,
        final int y,
        final int[][] points
    ) {
        final int xIdx = 0;
        final int yIdx = 1;
        int minDist = Integer.MAX_VALUE;
        int minDistIdx = -1;
        for (int i = 0; i < points.length; i++) {
            final int[] point = points[i];
            final int px = point[xIdx];
            final int py = point[yIdx];
            final int dist = Math.abs(x - px) + Math.abs(y - py);
            if (
                minDist > dist &&
                (
                    px == x ||
                    py == y
                )
            ) {
                minDist = dist;
                minDistIdx = i;
            }
        }
        return minDistIdx;
    }
}