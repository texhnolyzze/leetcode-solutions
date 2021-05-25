class Solution {
    public static final int UNUSED = -1;
    private int[] res;

    public int[] constructDistancedSequence(final int n) {
        final int[] temp = new int[2 * n - 1];
        res = new int[2 * n - 1];
        Arrays.fill(res, UNUSED);
        Arrays.fill(temp, UNUSED);
        final Num[] nums = new Num[n + 1];
        for (int i = 1; i <= n; i++) {
            nums[i] = new Num(i);
        }
        Arrays.sort(nums, 1, n + 1, (num1, num2) -> Integer.compare(num2.num, num1.num));
        constructDistancedSequence(
            nums,
            temp,
            n,
            0
        );
        return res;
    }

    private boolean constructDistancedSequence(
        final Num[] nums,
        int[] temp,
        final int numUnused,
        final int targetIdx
    ) {
        boolean found = false;
        if (numUnused == 0) {
            System.arraycopy(temp, 0, res, 0, temp.length);
            found = true;
        } else {
            for (int i = 1, numsLength = nums.length; i < numsLength; i++) {
                final Num num = nums[i];
                final int n = num.num;
                if (!num.used && validIdx(n, temp, targetIdx)) {
                    num.used = true;
                    temp[targetIdx] = n;
                    if (n != 1) {
                        temp[targetIdx + n] = n;
                    }
                    found = constructDistancedSequence(nums, temp, numUnused - 1, firstUnusedIndex(temp));
                    temp[targetIdx] = UNUSED;
                    if (n != 1) {
                        temp[targetIdx + n] = UNUSED;
                    }
                    if (found)
                        break;
                    num.used = false;
                }
            }
        }
        return found;
    }

    private int firstUnusedIndex(final int[] temp) {
        int res = -1;
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] == UNUSED)
                return i;
        }
        return res;
    }

    private boolean validIdx(
        final int n,
        final int[] temp,
        final int from
    ) {
        if (n == 1)
            return temp[from] == UNUSED;
        else {
            if (from + n >= temp.length)
                return false;
            return temp[from] == UNUSED && temp[from + n] == UNUSED;
        }
    }

    private int numDigits(final int n) {
        if (n >= 10)
            return 2;
        return 1;
    }

    private static class Num {
        final int num;
        boolean used;
        Num(final int num) {
            this.num = num;
        }
    }
}