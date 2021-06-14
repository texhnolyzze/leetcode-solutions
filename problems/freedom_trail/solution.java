class Solution {
    
    public int findRotateSteps(
        final String ring,
        final String key
    ) {
        final int[][] memtable = new int[ring.length()][key.length()];
        for (int i = 0; i < memtable.length; i++) {
            Arrays.fill(memtable[i], -1);
        }
        int min = Integer.MAX_VALUE;
        final char first = key.charAt(0);
        for (int i = 0; i < ring.length(); i++) {
            final char c = ring.charAt(i);
            if (c == first) {
                min = Math.min(min, i + findRotateSteps(ring, key, i, 0, memtable));
            }
        }
        for (int i = ring.length() - 1, j = 1; i >= 0; i--, j++) {
            final char c = ring.charAt(i);
            if (c == first) {
                min = Math.min(min, j + findRotateSteps(ring, key, i, 0, memtable));
            }
        }
        return min;
    }

    private int findRotateSteps(
        final String ring,
        final String key,
        final int ringPosition,
        final int alignTargetIdx,
        final int[][] memtable
    ) {
        if (alignTargetIdx == key.length() - 1) {
            return 1;
        }
        int res = memtable[ringPosition][alignTargetIdx];
        if (res != -1) {
            return res;
        }
        res = Integer.MAX_VALUE;
        final char next = key.charAt(alignTargetIdx + 1);
        for (int i = ringPosition, j = 0; true; j++) {
            if (ring.charAt(i) == next) {
                res = Math.min(res, 1 + j + findRotateSteps(ring, key, i, alignTargetIdx + 1, memtable));
            }
            i = rotateClockwise(ring, i);
            if (i == ringPosition) {
                break;
            }
        }
        for (int i = ringPosition, j = 0; true; j++) {
            if (ring.charAt(i) == next) {
                res = Math.min(res, 1 + j + findRotateSteps(ring, key, i, alignTargetIdx + 1, memtable));
            }
            i = rotateCounterClockwise(ring, i);
            if (i == ringPosition) {
                break;
            }
        }
        memtable[ringPosition][alignTargetIdx] = res;
        return res;
    }

    private int rotateClockwise(final String ring, final int from) {
        return from - 1 >= 0 ? from - 1 : ring.length() - 1;
    }

    private int rotateCounterClockwise(final String ring, final int from) {
        return (from + 1) % ring.length();
    }
    
}