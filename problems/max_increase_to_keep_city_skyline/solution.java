class Solution {
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int[] verticalSkyline = new int[grid.length];
        int[] horizontalSkyline = new int[grid.length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                int height = grid[i][j];
                horizontalSkyline[i] = Math.max(horizontalSkyline[i], height);
                verticalSkyline[j] = Math.max(verticalSkyline[j], height);
            }
        }
        int heightIncreaseTotal = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                int height = grid[i][j];
                int horizontalConstraint = horizontalSkyline[i];
                int verticalConstraint = verticalSkyline[j];
                heightIncreaseTotal += Math.min(horizontalConstraint, verticalConstraint) - height;
            }
        }
        return heightIncreaseTotal;
    }
}