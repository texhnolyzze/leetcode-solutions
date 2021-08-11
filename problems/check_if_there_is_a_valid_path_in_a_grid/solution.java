class Solution {
    public boolean hasValidPath(final int[][] grid) {
        final int h = grid.length;
        final int w = grid[0].length;
        final Queue<Integer> queue = new LinkedList<>();
        final BitSet seen = new BitSet(h * w);
        final int dest = (h - 1) * w + (w - 1);
        queue.add(0);
        while (!queue.isEmpty()) {
            final Integer node = queue.poll();
            if (node == dest) {
                return true;
            }
            final int y = node / w;
            final int x = node % w;
            final int streetType = grid[y][x];
            switch (streetType) {
                case 1 -> {
                    left(grid, w, queue, seen, y, x);
                    right(grid, w, queue, seen, y, x);
                }
                case 2 -> {
                    bottom(grid, h, w, queue, seen, y, x);
                    top(grid, w, queue, seen, y, x);
                }
                case 3 -> {
                    left(grid, w, queue, seen, y, x);
                    bottom(grid, h, w, queue, seen, y, x);
                }
                case 4 -> {
                    right(grid, w, queue, seen, y, x);
                    bottom(grid, h, w, queue, seen, y, x);
                }
                case 5 -> {
                    left(grid, w, queue, seen, y, x);
                    top(grid, w, queue, seen, y, x);
                }
                case 6 -> {
                    top(grid, w, queue, seen, y, x);
                    right(grid, w, queue, seen, y, x);
                }
                default -> throw new IllegalStateException("Unexpected value: " + streetType);
            }
        }
        return false;
    }

    private void top(
        final int[][] grid,
        final int w,
        final Queue<Integer> queue,
        final BitSet seen,
        final int y,
        final int x
    ) {
        if (y != 0) {
            final int top = grid[y - 1][x];
            if (top == 2 || top == 3 || top == 4) {
                final int idx = (y - 1) * w + x;
                if (!seen.get(idx)) {
                    seen.set(idx);
                    queue.add(idx);
                }
            }
        }
    }

    private void right(
        final int[][] grid,
        final int w,
        final Queue<Integer> queue,
        final BitSet seen,
        final int y,
        final int x
    ) {
        if (x != w - 1) {
            final int right = grid[y][x + 1];
            if (right == 1 || right == 3 || right == 5) {
                final int idx = y * w + x + 1;
                if (!seen.get(idx)) {
                    seen.set(idx);
                    queue.add(idx);
                }
            }
        }
    }

    private void bottom(
        final int[][] grid,
        final int h,
        final int w,
        final Queue<Integer> queue,
        final BitSet seen,
        final int y,
        final int x
    ) {
        if (y != h - 1) {
            final int bottom = grid[y + 1][x];
            if (bottom == 2 || bottom == 5 || bottom == 6) {
                final int idx = (y + 1) * w + x;
                if (!seen.get(idx)) {
                    seen.set(idx);
                    queue.add(idx);
                }
            }
        }
    }

    private void left(
        final int[][] grid,
        final int w,
        final Queue<Integer> queue,
        final BitSet seen,
        final int y,
        final int x
    ) {
        if (x != 0) {
            final int left = grid[y][x - 1];
            if (left == 1 || left == 4 || left == 6) {
                final int idx = y * w + x - 1;
                if (!seen.get(idx)) {
                    seen.set(idx);
                    queue.add(idx);
                }
            }
        }
    }
}