class Solution {
    public int removeBoxes(int[] boxes) {
        final int[][][] memtable = new int[boxes.length][boxes.length][boxes.length];
        return solve(boxes, 0, boxes.length - 1, 0, memtable);
    }

    private int solve(
        final int[] boxes,
        final int left,
        final int right,
        final int numBoxesFromLeft,
        final int[][][] memtable
    ) {
        if (left > right)
            return 0;
        else if (left == right)
            return (numBoxesFromLeft + 1) * (numBoxesFromLeft + 1);
        else {
            final int leftmostColor = boxes[left];
            int res = memtable[left][right][numBoxesFromLeft];
            if (res != 0)
                return res;
            res = (numBoxesFromLeft + 1) * (numBoxesFromLeft + 1) + solve(boxes, left + 1, right, 0, memtable);
            for (int i = left + 1; i <= right; i++) {
                final int color = boxes[i];
                if (color == leftmostColor) {
                    res = Math.max(
                        res,
                        solve(boxes, left + 1, i - 1, 0, memtable) + solve(boxes, i, right, numBoxesFromLeft + 1, memtable)
                    );
                }
            }
            memtable[left][right][numBoxesFromLeft] = res;
            return res;
        }
    }
}