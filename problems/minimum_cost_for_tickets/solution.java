class Solution {
    private final int dayPass = 0;
    private final int weekPass = 1;
    private final int monthPass = 2;

    private final int[] daysPerPassType = {
        1,
        7,
        30
    };

    public int mincostTickets(
        final int[] days,
        final int[] costs
    ) {
        final int[] memtable = new int[days.length];
        return mincostTickets(days, costs, 0, memtable);
    }

    private int mincostTickets(
        final int[] days,
        final int[] costs,
        final int dayIdx,
        final int[] memtable
    ) {
        if (dayIdx >= days.length) {
            return 0;
        }
        int res = memtable[dayIdx];
        if (res != 0) {
            return res;
        }
        res = Integer.MAX_VALUE;
        final int currDay = days[dayIdx];
        for (int passType = 0; passType < daysPerPassType.length; passType++) {
            final int daysPerPass = daysPerPassType[passType];
            int nextUncoveredDayIdx = dayIdx + 1;
            while (
                nextUncoveredDayIdx < days.length &&
                days[nextUncoveredDayIdx] - currDay < daysPerPass
            ) {
                nextUncoveredDayIdx++;
            }
            res = Math.min(
                res,
                costs[passType] + mincostTickets(days, costs, nextUncoveredDayIdx, memtable)
            );
        }
        memtable[dayIdx] = res;
        return res;
    }
}