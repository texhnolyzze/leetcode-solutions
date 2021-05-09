class Solution {
    public int stoneGameV(int[] stoneValue) {
        int[] prefixSum = getPrefixSum(stoneValue);
        int[][] memtable = new int[stoneValue.length][stoneValue.length];
        for (int i = 0; i < memtable.length; i++) {
            Arrays.fill(memtable[i], -1);
        }
        return stoneGameV(0, stoneValue.length - 1, prefixSum, memtable);
    }

    private int stoneGameV(
        final int left,
        final int right,
        final int[] prefixSum,
        final int[][] memtable
    ) {
        int res = memtable[left][right];
        if (res != -1)
            return res;
        if (left == right) {
            res = 0;
        } else {
            int max = 0;
            for (int i = left; i < right; i++) {
                int leftSubArraySum = getSubArraySum(prefixSum, left, i);
                int rightSubArraySum = getSubArraySum(prefixSum, i + 1, right);
                int candidate;
                if (leftSubArraySum < rightSubArraySum) {
                    candidate = stoneGameV(left, i, prefixSum, memtable) + leftSubArraySum;
                } else if (leftSubArraySum > rightSubArraySum) {
                    candidate = stoneGameV(i + 1, right, prefixSum, memtable) + rightSubArraySum;
                } else {
                    candidate = Math.max(
                        stoneGameV(left, i, prefixSum, memtable),
                        stoneGameV(i + 1, right, prefixSum, memtable)
                    ) + leftSubArraySum;
                }
                max = Math.max(max, candidate);
            }
            res = max;
        }
        memtable[left][right] = res;
        return res;
    }

    private int getSubArraySum(int[] prefixSum, int from, int to) {
        return prefixSum[to] - (from == 0 ? 0 : prefixSum[from - 1]);
    }

    private int[] getPrefixSum(final int[] stoneValue) {
        int[] prefixSum = new int[stoneValue.length];
        int sum = 0;
        for (int i = 0; i < stoneValue.length; i++) {
            sum += stoneValue[i];
            prefixSum[i] = sum;
        }
        return prefixSum;
    }
}