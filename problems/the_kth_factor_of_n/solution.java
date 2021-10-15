class Solution {
    public int kthFactor(
        final int n,
        final int k
    ) {
        int serial = 0;
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                serial++;
                if (serial == k) {
                    return i;
                }
            }
        }
        return -1;
    }
}