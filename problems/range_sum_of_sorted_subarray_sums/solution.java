class Solution {
    private static final int MOD = (int) (1E9 + 7);

    public int rangeSum(
        final int[] nums,
        final int n,
        final int left,
        final int right
    ) {
        int[] prev = new int[n];
        int[] curr = new int[n];
        int pos = 0;
        final int[] all = new int[n * (n + 1) / 2];
        for (int i = 0; i < n; i++) {
            final int num = nums[i];
            prev[i] = num;
            all[pos++] = num;
        }
        for (int len = 1; len < n; len++) {
            for (int i = 0; i < n - len; i++) {
                final int subsum = prev[i] + nums[i + len];
                all[pos++] = subsum;
                curr[i] = subsum;
            }
            final int[] temp = prev;
            prev = curr;
            curr = temp;
        }
        Arrays.sort(all);
        return Arrays.stream(all, left - 1, right).reduce((n1, n2) -> (n1 + n2) % MOD).orElseThrow();
    }
}