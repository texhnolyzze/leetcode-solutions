class Solution {
    public int lenLongestFibSubseq(final int[] arr) {
        final Map<Integer, Integer> idx = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            idx.put(arr[i], i);
        }
        int max = 0;
        for (int i = 0; i < arr.length - 2; i++) {
            for (int j = i + 1; j < arr.length - 1; j++) {
                int prevprev = arr[i];
                int prev = arr[j];
                int len = 2;
                while (true) {
                    int sum = prevprev + prev;
                    final Integer next = idx.get(sum);
                    if (next == null) {
                        break;
                    }
                    len++;
                    prevprev = prev;
                    prev = arr[next];
                }
                if (len > 2) {
                    max = Math.max(max, len);
                }
            }
        }
        return max;
    }
}