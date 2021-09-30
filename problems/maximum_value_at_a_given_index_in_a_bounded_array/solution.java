class Solution {
    public int maxValue(
        final int n,
        final int index,
        final int maxSum
    ) {
        return spawn(n, index, maxSum, 1, (maxSum - 1) / n);
    }

    private int spawn(
        final int n,
        final int index,
        final int maxSum,
        final int leftBaseBound,
        final int rightBaseBound
    ) {
        int max = 1;
        int l = leftBaseBound;
        int r = rightBaseBound;
        while (l <= r) {
            final int midBase = l + (r - l) / 2;
            final int midValue = midBase + increment(n, index, maxSum, midBase);
            final int lValue;
            if (midBase - 1 >= l) {
                lValue = (midBase - 1) + increment(n, index, maxSum, midBase - 1);
            } else {
                lValue = Integer.MIN_VALUE;
            }
            final int rValue;
            if (midBase + 1 <= r) {
                rValue = (midBase + 1) + increment(n, index, maxSum, midBase + 1);
            } else {
                rValue = Integer.MIN_VALUE;
            }
            if (midValue > lValue) {
                if (midValue > rValue) {
                    return midValue;
                } else {
                    max = Math.max(max, rValue);
                    l = midBase + 1;
                }
            } else {
                if (lValue > rValue) {
                    max = Math.max(max, lValue);
                    r = midBase - 1;
                } else {
                    if (lValue == rValue) {
                        return Math.max(
                            spawn(n, index, maxSum, l, midBase - 1),
                            spawn(n, index, maxSum, midBase + 1, r)
                        );
                    } else {
                        max = Math.max(max, rValue);
                        l = midBase + 1;
                    }
                }
            }
        }
        return max;
    }

    private int increment(
        final int n,
        final int index,
        final int maxSum,
        final int base
    ) {
        int remained = maxSum - (base * n + 1);
        int offset = 1;
        int increment = 1;
        while (true) {
            if (index - offset >= 0) {
                if (index + offset < n) {
                    if (remained >= 2 * increment + 1) {
                        remained = remained - (2 * increment + 1);
                    } else {
                        break;
                    }
                } else {
                    if (remained >= increment + 1 + (n - index - 1)) {
                        remained = remained - (increment + 1 + (n - index - 1));
                    } else {
                        break;
                    }
                }
            } else {
                if (index + offset < n) {
                    if (remained >= increment + 1 + index) {
                        remained = remained - (increment + 1 + index);
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
            offset++;
            increment++;
        }
        return increment;
    }
}