import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

class Solution {
        
    private final int n;
    private final Interval[] intervals;
    public Solution(int n, int[] blacklist) {
        Object[] params = buildIntervals(blacklist, n);
        this.n = (Integer) params[0];
        this.intervals = (Interval[]) params[1];
    }

    private Object[] buildIntervals(int[] blacklist, int n) {
        if (blacklist.length == 0) 
                return new Object[] {n, new Interval[] {new Interval(0, n - 1, 0, n - 1)}};
            List<Interval> l = new ArrayList<>();
            Arrays.sort(blacklist);
            Interval leftmost = getLeftmost(blacklist);
            int normalizedOffset = 0;
            if (leftmost != null) {
                normalizedOffset = leftmost.right + 1;
                l.add(leftmost);
            }
            Interval last = leftmost == null ? null : leftmost;
            for (int i = 0; i < blacklist.length - 1; i++) {
                int len = blacklist[i + 1] - blacklist[i] - 2;
                if (len >= 0) {
                    int left = blacklist[i] + 1;
                    int right = blacklist[i + 1] - 1;
                    int leftNormalized = normalizedOffset;
                    int rightNormalized = normalizedOffset + len;
                    normalizedOffset += (len + 1);
                    last = new Interval(left, right, leftNormalized, rightNormalized);
                    l.add(last);
                }
            }
            Interval rightmost = getRightmost(blacklist, last, n);
            if (rightmost != null) {
                normalizedOffset = rightmost.rightNormalized + 1;
                l.add(rightmost);
            }
            return new Object[] {normalizedOffset, l.toArray(new Interval[l.size()])};
    }
    
    private Interval getLeftmost(int[] blacklist) {
            int len = blacklist[0] - 1;
            if (len >= 0) 
                return new Interval(0, blacklist[0] - 1, 0, blacklist[0] - 1);
            return null;
        }
        
        private Interval getRightmost(int[] blacklist, Interval last, int n) {
            int right = n - 1;
            int left = blacklist[blacklist.length - 1] + 1;
            int len = right - left + 1;
            if (len > 0) {
                if (last != null)
                    return new Interval(left, right, last.rightNormalized + 1, last.rightNormalized + len);
                else
                    return new Interval(left, right, 0, len - 1);
            }
            return null;
        }

    private final ThreadLocalRandom rand = ThreadLocalRandom.current();
    public int pick() {
        int p = rand.nextInt(n);
        int l = 0, r = intervals.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            Interval i = intervals[mid];
            if (p < i.leftNormalized) 
                r = mid - 1;
            else if (p > i.rightNormalized)
                l = mid + 1;
            else
                return rand.nextInt(i.left, i.right + 1);
        }
        return -1; // can't happen
    }
    
    class Interval {
        final int left, right;
        final int leftNormalized, rightNormalized;
        Interval(int left, int right, int leftNormalized, int rightNormalized) {
            this.left = left;
            this.right = right;
            this.leftNormalized = leftNormalized;
            this.rightNormalized = rightNormalized;
        }
    }
    
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(N, blacklist);
 * int param_1 = obj.pick();
 */