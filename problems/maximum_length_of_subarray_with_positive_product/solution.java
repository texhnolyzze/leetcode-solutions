class Solution {
    public int getMaxLen(final int[] nums) {
        int max = 0;
        int leftNonZero = -1;
        for (int i = 0; i < nums.length; i++) {
            final int num = nums[i];
            if (num == 0) {
                if (leftNonZero != -1) {
                    max = Math.max(
                        max,
                        getMaxLen(
                            nums,
                            leftNonZero,
                            i - 1
                        )
                    );
                    leftNonZero = -1;
                }
            } else {
                if (leftNonZero == -1) {
                    leftNonZero = i;
                }
            }
        }
        if (leftNonZero != -1) {
            max = Math.max(
                max,
                getMaxLen(
                    nums,
                    leftNonZero,
                    nums.length - 1
                )
            );
        }
        return max;
    }

    private int getMaxLen(
        final int[] nums,
        final int leftNonZero,
        final int rightNonZero
    ) {
        final long negativeCount = Arrays.stream(nums, leftNonZero, rightNonZero + 1).filter(n -> n < 0).count();
        if (negativeCount % 2 == 0) {
            return rightNonZero - leftNonZero + 1;
        } else {
            int leftmostNegative = -1;
            int rightmostNegative = -1;
            for (int i = leftNonZero; i <= rightNonZero; i++) {
                if (nums[i] < 0) {
                    leftmostNegative = i;
                    break;
                }
            }
            for (int i = rightNonZero; i >= leftNonZero; i--) {
                if (nums[i] < 0) {
                    rightmostNegative = i;
                    break;
                }
            }
            return Math.max(rightNonZero - (leftmostNegative + 1) + 1, (rightmostNegative - 1) - leftNonZero + 1);
        }
    }
}