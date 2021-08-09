class Solution {
    public int findMinArrowShots(final int[][] intervals) {
        final int leftIdx = 0;
        final int rightIdx = 1;
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[leftIdx]));
        int min = 0;
        for (int i = 0; i < intervals.length; i++) {
            final int[] targetInterval = intervals[i];
            int targetLeft = targetInterval[leftIdx];
            int targetRight = targetInterval[rightIdx];
            for (int j = i + 1; j < intervals.length; j++) {
                final int[] otherInterval = intervals[j];
                final int otherLeft = otherInterval[leftIdx];
                final int otherRight = otherInterval[rightIdx];
                if (targetLeft <= otherLeft && targetRight >= otherRight) {
                    targetLeft = otherLeft;
                    targetRight = otherRight;
                    i = j;
                } else if (otherLeft > targetRight) {
                    i = j - 1;
                    break;
                } else {
                    i = j;
                }
            }
            min++;
        }
        return min;
    }
}