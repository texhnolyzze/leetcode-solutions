class Solution {
    public int findKthNumber(
        final int n,
        final int k
    ) {
        final int kZeroIndexed = k - 1;
        int ans = 0;
        int index = -1;
        while (index != kZeroIndexed) {
            for (int dig = ans == 0 ? 1 : 0; dig < 10; dig++) {
                final int count = count(n, ans * 10 + dig);
                if (index + count < kZeroIndexed) {
                    index += count;
                } else {
                    index++;
                    ans = 10 * ans + dig;
                    break;
                }
            }
        }
        return ans;
    }

    private int count(
        final int bound,
        final long num
    ) {
        long cnt = 1;
        long curr = num;
        long temp = 1;
        while (curr * 10 + 9 <= bound) {
            cnt = cnt + temp * 10;
            temp *= 10;
            curr = curr * 10 + 9;
        }
        curr = num * temp * 10;
        if (bound - curr >= 0) {
            cnt += (bound - curr + 1);
        }
        return (int) cnt;
    }
}