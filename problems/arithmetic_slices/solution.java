class Solution {
    public int numberOfArithmeticSlices(final int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length - 2;) {
            final int diff = nums[i + 1] - nums[i];
            int sliceLen = 2;
            for (int k = i + 2; k < nums.length; k++) {
                if (nums[k] - nums[k - 1] == diff) {
                    i = k - 1;
                    sliceLen++;
                } else {
                    if (sliceLen > 2) {
                        i = k - 1;
                    }
                    break;
                }
            }
            if (sliceLen == 2) {
                i++;
            } else {
                final int n = sliceLen - 2;
                res = res + (n * (n + 1)) / 2;
            }
        }
        return res;
    }
}