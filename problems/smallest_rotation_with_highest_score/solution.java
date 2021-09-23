class Solution {
    public int bestRotation(final int[] nums) {
        final int n = nums.length;
        final RangeSum rangeSum = new RangeSum(n);
        for (int i = 0; i < nums.length; i++) {
            final int num = nums[i];
            if (num == 0) {
                continue;
            }
            if (num <= i) {
                rangeSum.increment(
                    i - (num - 1),
                    i
                );
            } else {
                rangeSum.increment(
                    0,
                    i
                );
                if (i != nums.length - 1 && num > i + 1) {
                    rangeSum.increment(
                        i + n - num + 1,
                        n - 1
                    );
                }
            }
        }
        int kMin = -1;
        int minIntersections = -1;
        for (int k = 0; k < n; k++) {
            final int intersections = rangeSum.value(k);
            if (kMin == -1 || minIntersections > intersections) {
                kMin = k;
                minIntersections = intersections;
            }
        }
        return kMin;
    }

    private static class RangeSum {

        private final FenwickTree tree;

        RangeSum(final int n) {
            this.tree = new FenwickTree(n + 1);
        }

        void increment(final int left, final int right) {
            tree.update(left, 1);
            tree.update(right + 1, -1);
        }

        int value(final int idx) {
            return tree.sum(idx);
        }

    }

    private static class FenwickTree {

        final int[] arr;

        FenwickTree(final int n) {
            this.arr = new int[n + 1];
        }

        void update(int x, final int delta ) {
            x++;
            while (x < arr.length) {
                arr[x] += delta;
                x += x & -x;
            }
        }

        int sum(int i) {
            i++;
            int res = 0;
            while (i > 0) {
                res += arr[i];
                i -= i & -i;
            }
            return res;
        }

    }
}