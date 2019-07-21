class Solution {
    public static int canCompleteCircuit(final int[] gas, final int[] cost) {
        Integer[] indices = new Integer[gas.length];
        for (int i = 0; i < indices.length; i++)
            indices[i] = i;
        Arrays.sort(indices, (i1, i2) -> {
            double r1 = ((double) gas[i1]) / cost[i1];
            double r2 = ((double) gas[i2]) / cost[i2];
            return -Double.compare(r1, r2);
        });
        outer: for (Integer idx : indices) {
            if (gas[idx] < cost[idx])
                return -1;
            int tank = 0;
            for (int i = idx, n = 0; n < gas.length; i = (i + 1) % gas.length, n++) {
                tank += gas[i];
                if (tank < cost[i])
                    continue outer;
                tank -= cost[i];
            }
            return idx;
        }
        return -1;
    }
}