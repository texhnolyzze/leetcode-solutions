class Solution {
    public int maxCount(int m, int n, int[][] ops) {
        if (ops.length == 0)
            return m * n;
        int[] temp = ops[0];
        for (int i = 1; i < ops.length; i++)
            intersect(temp, ops[i]);
        return temp[0] * temp[1];
    }

    private static void intersect(int[] temp, int[] op) {
        temp[0] = Math.min(temp[0], op[0]);
        temp[1] = Math.min(temp[1], op[1]);
    }
}