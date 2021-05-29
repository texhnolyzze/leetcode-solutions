class Solution {
    private static final int START = 0;
    private static final int END = 1;

    public int[] findRightInterval(final int[][] intervals) {
        final Integer[] sortedIndices = new Integer[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            sortedIndices[i] = i;
        }
        Arrays.sort(sortedIndices, Comparator.comparingInt(idx -> intervals[idx][START]));
        sortByIndices(intervals, sortedIndices);
        final int[] rightIntervalId = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            final int right = findRight(intervals, i);
            rightIntervalId[sortedIndices[i]] = right == -1 ? -1 : sortedIndices[right];
        }
        return rightIntervalId;
    }

    private void sortByIndices(
        final int[][] intervals,
        final Integer[] sortedIndices
    ) {
        final BitSet seen = new BitSet(intervals.length);
        for (int i = 0; i < intervals.length; i++) {
            if (seen.get(i))
                continue;
            final int idx = sortedIndices[i];
            if (idx != i) {
                final int[] tempInterval = intervals[i];
                int j = i;
                while (sortedIndices[j] != i) {
                    intervals[j] = intervals[sortedIndices[j]];
                    seen.set(j);
                    j = sortedIndices[j];
                }
                intervals[j] = tempInterval;
                seen.set(j);
            }
        }
    }

    private int findRight(final int[][] intervals, final int idx) {
        final int[] interval = intervals[idx];
        final int targetEnd = interval[END];
        if (targetEnd == interval[START])
            return idx;
        int left = idx + 1;
        int right = intervals.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            final int candidateStart = intervals[mid][START];
            if (candidateStart >= targetEnd) {
                if (mid == left || intervals[mid - 1][START] < targetEnd)
                    return mid;
                else {
                    right = mid - 1;
                }
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}