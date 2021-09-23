class Solution {
    public int nearestExit(
        final char[][] maze,
        final int[] entrance
    ) {
        final char wall = '+';
        final int m = maze.length;
        final int n = maze[0].length;
        final BitSet seen = new BitSet(m * n);
        final Queue<Integer> queue = new LinkedList<>();
        final int[] steps = new int[m * n];
        final int yStart = entrance[0];
        final int xStart = entrance[1];
        final int start = yStart * n + xStart;
        seen.set(start);
        queue.add(start);
        steps[start] = 0;
        while (!queue.isEmpty()) {
            final int curr = queue.poll();
            final int y = curr / n;
            final int x = curr % n;
            final int currSteps = steps[curr];
            if (
                (y != yStart || x != xStart) &&
                (x == 0 || y == 0 || x == n - 1 || y == m - 1)
            ) {
                return currSteps;
            }
            if (y != 0 && maze[y - 1][x] != wall && !seen.get((y - 1) * n + x)) {
                final int next = (y - 1) * n + x;
                steps[next] = currSteps + 1;
                seen.set(next);
                queue.add(next);
            }
            if (y != m - 1 && maze[y + 1][x] != wall && !seen.get((y + 1) * n + x)) {
                final int next = (y + 1) * n + x;
                steps[next] = currSteps + 1;
                seen.set(next);
                queue.add(next);
            }
            if (x != 0 && maze[y][x - 1] != wall && !seen.get(y * n + x - 1)) {
                final int next = y * n + x - 1;
                steps[next] = currSteps + 1;
                seen.set(next);
                queue.add(next);
            }
            if (x != n - 1 && maze[y][x + 1] != wall && !seen.get(y * n + x + 1)) {
                final int next = y * n + x + 1;
                steps[next] = currSteps + 1;
                seen.set(next);
                queue.add(next);
            }
        }
        return -1;
    }
}