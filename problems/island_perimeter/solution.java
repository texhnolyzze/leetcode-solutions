class Solution {
    private int[][] grid;
    private int perimeter;
    public int islandPerimeter(int[][] grid) {
        this.grid = grid;
        outer: for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    dfs(i, j);
                    break outer;
                }
            }
        }
        perimeter += 4 * numNeighboursToNumCeils.getOrDefault(0, 0);
        perimeter += 3 * numNeighboursToNumCeils.getOrDefault(1, 0);
        perimeter += 2 * numNeighboursToNumCeils.getOrDefault(2, 0);
        perimeter += numNeighboursToNumCeils.getOrDefault(3, 0);
        return perimeter;
    }

    private final Map<Integer, Integer> numNeighboursToNumCeils = new HashMap<>();
    private void dfs(int i, int j) {
        grid[i][j] = -1;
        numNeighboursToNumCeils.merge(getNumNeighbours(i, j), 1, Integer::sum);
        if (i - 1 >= 0 && grid[i - 1][j] == 1) dfs(i - 1, j);
        if (j - 1 >= 0 && grid[i][j - 1] == 1) dfs(i, j - 1);
        if (i + 1 < grid.length && grid[i + 1][j] == 1) dfs(i + 1, j);
        if (j + 1 < grid[0].length && grid[i][j + 1] == 1) dfs(i, j + 1);
    }
    
    private int getNumNeighbours(int i, int j) {
        int numNeighbours = 0;
        if (i - 1 >= 0 && Math.abs(grid[i - 1][j]) == 1) numNeighbours++;
        if (j - 1 >= 0 && Math.abs(grid[i][j - 1]) == 1) numNeighbours++;
        if (i + 1 < grid.length && Math.abs(grid[i + 1][j]) == 1) numNeighbours++;
        if (j + 1 < grid[0].length && Math.abs(grid[i][j + 1]) == 1) numNeighbours++;
        return numNeighbours;
    }
}