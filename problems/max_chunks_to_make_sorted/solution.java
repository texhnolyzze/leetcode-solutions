class Solution {
    public int maxChunksToSorted(final int[] arr) {
        final int n = arr.length;
        final int[] sorted = Arrays.copyOf(arr, n);
        Arrays.sort(sorted);
        int pos = 0;
        int res = 0;
        while (pos != n) {
            final int next = sorted[pos];
            final int idx = find(arr, pos, next);
            final int chunkLen = idx - pos + 1;
            for (int len = chunkLen; pos + len - 1 < n; len++) {
                final int[] copy = Arrays.copyOfRange(arr, pos, pos + len);
                Arrays.sort(copy);
                boolean valid = true;
                for (int i = 0; i < copy.length; i++) {
                    if (copy[i] != sorted[pos + i]) {
                        valid = false;
                        break;
                    }
                }
                if (valid) {
                    res++;
                    pos += len;
                    break;
                }
            }
        }
        return res;
    }

    private int find(
        final int[] arr,
        final int from,
        final int n
    ) {
        for (int i = from; i < arr.length; i++) {
            if (arr[i] == n) {
                return i;
            }
        }
        throw new IllegalStateException();
    }
}