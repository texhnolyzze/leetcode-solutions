class Solution {

    private final int totalPoints;
    private final int[][] rectangles;
    private final TreeMap<Integer, Integer> pointWatermark;

    public Solution(final int[][] rectangles) {
        this.rectangles = rectangles;
        this.pointWatermark = new TreeMap<>();
        int numPoints = 0;
        for (int rectangleIdx = 0; rectangleIdx < rectangles.length; rectangleIdx++) {
            pointWatermark.put(numPoints, rectangleIdx);
            final int width = rectangles[rectangleIdx][2] - rectangles[rectangleIdx][0];
            final int height = rectangles[rectangleIdx][3] - rectangles[rectangleIdx][1];
            numPoints = numPoints + (width + 1) * (height + 1);
        }
        this.totalPoints = numPoints;
    }

    public int[] pick() {
        final int p = ThreadLocalRandom.current().nextInt(0, totalPoints);
        final Map.Entry<Integer, Integer> entry = pointWatermark.floorEntry(p);
        int rectangleIdx = entry.getValue();
        final int[] rectangle = rectangles[rectangleIdx];
        final int pointIdx = p - entry.getKey();
        final int width = (rectangle[2] - rectangle[0]) + 1;
        return new int[] {
            rectangle[0] + pointIdx % width,
            rectangle[1] + pointIdx / width
        };
    }
    
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(rects);
 * int[] param_1 = obj.pick();
 */