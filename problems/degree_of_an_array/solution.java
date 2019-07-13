class Solution {
    public int findShortestSubArray(int[] nums) {
        int degree = 0;
        Map<Integer, Integer> countOf = new HashMap<>();
        Map<Integer, int[]> boundsOf = new HashMap<>();
        for (int[] i = {0}; i[0] < nums.length; i[0]++) {
            degree = Math.max(degree, countOf.merge(nums[i[0]], 1, Integer::sum));
            boundsOf.compute(nums[i[0]], (key, u) -> {
                if (u == null)
                    return new int[] {i[0], i[0]};
                else
                    u[1] = i[0];
                return u;
            });
        }
        int minLen = nums.length;
        for (Map.Entry<Integer, int[]> e : boundsOf.entrySet()) {
            if (countOf.get(e.getKey()) == degree) {
                int[] bounds = e.getValue();
                minLen = Math.min(minLen, bounds[1] - bounds[0] + 1);
            }
        }
        return minLen;
    }
}