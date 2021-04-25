import java.util.*;

public class Solution {

    public int maxValue(int[][] events, int k) {
        Arrays.sort(events, Comparator.comparingInt(event -> event[0]));
        int[][] memo = new int[k + 1][events.length + 1];
        for (int[] arr : memo) {
            Arrays.fill(arr, -1);
        }
        return max(events, k, 0, memo);
    }

    private int max(int[][] events, int k, int idx, int[][] memo) {
        if (k == 0 || idx > events.length - 1 || idx < 0)
            return 0;
        if (memo[k][idx] != -1)
            return memo[k][idx];
        int currEventEndDay = events[idx][1];
        int currEventValue = events[idx][2];
        int nextAvailableEventIdx = nextAvailableEventIdx(events, idx + 1, events.length - 1, currEventEndDay);
        int max = Math.max(
            currEventValue + max(events, k - 1, nextAvailableEventIdx, memo),
            max(events, k, idx + 1, memo)
        );
        memo[k][idx] = max;
        return max;
    }

    private int nextAvailableEventIdx(int[][] events, int l, int r, int usedEndDay) {
        if (r >= l) {
            int mid = l + (r - l) / 2;
            if (events[mid][0] > usedEndDay) {
                if (events[mid - 1][0] <= usedEndDay)
                    return mid;
            }
            if (events[mid][0] > usedEndDay)
                return nextAvailableEventIdx(events, l, mid - 1, usedEndDay);
            return nextAvailableEventIdx(events, mid + 1, r, usedEndDay);
        }
        return -1;
    }

}