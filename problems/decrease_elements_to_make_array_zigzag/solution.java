class Solution {
    public int movesToMakeZigzag(final int[] nums) {
        return Math.min(
            evenGreater(nums),
            oddGreater(nums)
        );
    }

    private int oddGreater(final int[] nums) {
        int moves = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                final int num = nums[i];
                final int prev = i - 1 >= 0 ? nums[i - 1] : Integer.MAX_VALUE;
                final int next = i + 1 < nums.length ? nums[i + 1] : Integer.MAX_VALUE;
                moves += moves(num, prev, next);
            }
        }
        return moves;
    }

    private int evenGreater(final int[] nums) {
        int moves = 0;
        for (int i = 1; i < nums.length; i++) {
            if (i % 2 != 0) {
                final int num = nums[i];
                final int prev = nums[i - 1];
                final int next = i + 1 < nums.length ? nums[i + 1] : Integer.MAX_VALUE;
                moves += moves(num, prev, next);
            }
        }
        return moves;
    }

    private int moves(
        final int num,
        final int prev,
        final int next
    ) {
        if (prev <= num) {
            if (next <= num) {
                return num - Math.min(prev, next) + 1;
            } else {
                return num - prev + 1;
            }
        } else {
            if (next <= num) {
                return num - next + 1;
            } else {
                return 0;
            }
        }
    }
}