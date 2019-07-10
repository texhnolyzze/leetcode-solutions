class Solution {
    public static int orangesRotting(int[][] grid) {
        TimedIntVec2[] temp = new TimedIntVec2[4];
        for (int i = 0; i < 4; i++) temp[i] = new TimedIntVec2(-1, -1, -1);
        boolean[] valid = new boolean[4];
        int m = grid.length;
        int n = grid[0].length;
        boolean containsFresh = false;
        Queue<TimedIntVec2> q = new LinkedList<>();
        for (int y = 0; y < m; y++) {
            for (int x = 0; x < n; x++) {
                if (grid[y][x] == 1)
                    containsFresh = true;
                else if (grid[y][x] == 2) {
                    TimedIntVec2 v = new TimedIntVec2(x, y, 0);
                    q.add(v);
                }
            }
        }
        if (q.isEmpty()) return containsFresh ? -1 : 0;
        while (true) {
            TimedIntVec2 v = q.poll();
            getNeighbours(v.y, v.x, m, n, temp, valid);
            for (int i = 0; i < 4; i++) {
                if (!valid[i])
                    continue;
                int nx = temp[i].x; 
                int ny = temp[i].y;
                if (grid[ny][nx] == 1) {
                    grid[ny][nx] = 2;
                    q.add(new TimedIntVec2(nx, ny, v.time + 1));
                }
            }
            if (q.isEmpty()) {
                for (int y = 0; y < m; y++) {
                    for (int x = 0; x < n; x++) {
                        if (grid[y][x] == 1) {
                            return -1;
                        }
                    }
                }
                return v.time;                
            }
        }
    }

    private static void getNeighbours(int y, int x, int m, int n, TimedIntVec2[] temp, boolean[] valid) {
        if (y - 1 < 0) valid[0] = false;
        else {temp[0].x = x; temp[0].y = y - 1; valid[0] = true; } // upper neighbour
        if (x + 1 >= n) valid[1] = false;
        else {temp[1].x = x + 1; temp[1].y = y; valid[1] = true;} // right neighbour
        if (y + 1 >= m) valid[2] = false;
        else {temp[2].x = x; temp[2].y = y + 1; valid[2] = true;} // bottom neighbour
        if (x - 1 < 0) valid[3] = false;
        else {temp[3].x = x - 1; temp[3].y = y; valid[3] = true;} // left neighbour
    }
    
    private static class TimedIntVec2 {
        private int time;
        private int x, y;
        private TimedIntVec2(int x, int y, int time) {
            this.x = x; 
            this.y = y; 
            this.time = time;
        }
    }
}