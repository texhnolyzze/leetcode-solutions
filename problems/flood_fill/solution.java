class Solution {
    private int[][] image;
    private boolean[][] visited;
    private int startingPixelColor;
    private int newColor;
    
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        this.image = image;
        visited = new boolean[image.length][image[0].length];
        this.startingPixelColor = image[sr][sc];
        this.newColor = newColor;
        dfs(sr, sc);
        return image;
    }

    private void dfs(int y, int x) {
        visited[y][x] = true;
        if (image[y][x] != startingPixelColor)
            return;
        image[y][x] = newColor;
        if (y - 1 >= 0 && !visited[y - 1][x])
            dfs(y - 1, x);
        if (y + 1 < image.length && !visited[y + 1][x])
            dfs(y + 1, x);
        if (x - 1 >= 0 && !visited[y][x - 1])
            dfs(y, x - 1);
        if (x + 1 < image[0].length && !visited[y][x + 1])
            dfs(y, x + 1);
    }
}