class Solution {
    public int tilingRectangle(int height, int width) {
        boolean[][] maze = new boolean[height][width];
        return tilingRectangle(maze, 0, new HashMap<>());
    }

    private BitSet toBitSet(boolean[][] maze) {
        BitSet bitSet = new BitSet(maze.length * maze[0].length);
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                bitSet.set(i * maze.length + j, maze[i][j]);
            }
        }
        return bitSet;
    }

    private int tilingRectangle(final boolean[][] maze, final int numSquaresSoFar, Map<BitSet, Integer> memtable) {
        if (numSquaresSoFar >= Math.max(maze.length, maze[0].length)) {
            if (allCovered(maze))
                return numSquaresSoFar;
            else
                return Integer.MAX_VALUE;
        } else {
            if (allCovered(maze))
                return numSquaresSoFar;
        }
        BitSet key = toBitSet(maze);
        Integer min = memtable.get(key);
        if (min != null)
            return min;
        min = Integer.MAX_VALUE;
        outer: for (int y = 0; y < maze.length; y++) {
            for (int x = 0; x < maze[0].length; x++) {
                if (!maze[y][x]) {
                    int maxSide = getMaxSidePossible(maze, y, x);
                    for (int side = maxSide; side >= 1; side--) {
                        cover(maze, y, x, side, true);
                        min = Math.min(
                            min,
                            tilingRectangle(maze, numSquaresSoFar + 1, memtable)
                        );
                        cover(maze, y, x, side, false);
                    }
                    break outer;
                }
            }
        }
        memtable.put(key, min);
        return min;
    }

    private boolean allCovered(final boolean[][] maze) {
        for (int y = 0; y < maze.length; y++) {
            for (int x = 0; x < maze[y].length; x++) {
                if (!maze[y][x])
                    return false;
            }
        }
        return true;
    }

    private void cover(
        final boolean[][] maze,
        final int y,
        final int x,
        final int side,
        final boolean cover
    ) {
        for (int i = 0; i < side; i++) {
            for (int j = 0; j < side; j++) {
                maze[y + i][x + j] = cover;
            }
        }
    }

    private int getMaxSidePossible(final boolean[][] maze, final int y, final int x) {
        int side = 1;
        while (valid(maze, y, x, side)) {
            side++;
        }
        return side;
    }

    private boolean valid(
        final boolean[][] maze,
        final int y,
        final int x,
        final int side
    ) {
        if (y + side >= maze.length || x + side >= maze[0].length)
            return false;
        for (int i = y; i <= y + side; i++) {
            for (int j = x; j <= x + side; j++) {
                if (maze[i][j])
                    return false;
            }
        }
        return true;
    }
}