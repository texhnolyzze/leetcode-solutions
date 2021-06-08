class SummaryRanges {

    private final TreeMap<Integer, int[]> intervals;

    public SummaryRanges() {
        intervals = new TreeMap<>();
    }

    public void addNum(final int num) {
        final Map.Entry<Integer, int[]> floor = intervals.floorEntry(num);
        if (floor != null) {
            floor(num, floor);
        } else {
            ceiling(num);
        }
    }

    private void floor(final int num, final Map.Entry<Integer, int[]> floor) {
        final int[] interval;
        interval = floor.getValue();
        if (contains(interval, num))
            return;
        if (num - interval[1] == 1) {
            interval[1] = num;
            final Map.Entry<Integer, int[]> ceiling = intervals.ceilingEntry(num);
            if (ceiling != null) {
                final int[] ceilingInterval = ceiling.getValue();
                if (ceilingInterval[0] - interval[1] == 1) {
                    interval[1] = ceilingInterval[1];
                    intervals.remove(ceiling.getKey());
                }
            }
        } else {
            ceiling(num);
        }
    }

    private void ceiling(final int num) {
        final int[] interval;
        final Map.Entry<Integer, int[]> ceiling = intervals.ceilingEntry(num);
        if (ceiling == null) {
            intervals.put(num, new int[] {num, num});
        } else {
            interval = ceiling.getValue();
            if (contains(interval, num))
                return;
            if (interval[0] - num == 1) {
                interval[0] = num;
            } else {
                intervals.put(num, new int[] {num, num});
            }
        }
    }

    private boolean contains(final int[] interval, final int num) {
        return interval[0] <= num && num <= interval[1];
    }

    public int[][] getIntervals() {
        return intervals.values().toArray(int[][]::new);
    }
    
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * int[][] param_2 = obj.getIntervals();
 */