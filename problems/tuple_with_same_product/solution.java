class Solution {
    public int tupleSameProduct(final int[] nums) {
        final Map<Integer, Integer> pairProductFreq = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            final int num1 = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                pairProductFreq.compute(
                    num1 * nums[j],
                    (unused, freq) -> freq == null ? 1 : freq + 1
                );
            }
        }
        int res = 0;
        for (final int productFreq : pairProductFreq.values()) {
            res = res + 8 * ((productFreq) * (productFreq - 1) / 2);
        }
        return res;
    }
}