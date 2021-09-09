class Solution {
    public int lastRemaining(final int n) {
        if (n == 1) {
            return 1;
        }
        final int log2 = log2(n);
        int leftBound = 2;
        int rightBound = n % 2 == 0 ? n : n - 1;
        int numPasses = 1;
        boolean leftToRight = false;
        int neighboursDiff = 2;
        while (numPasses != log2) {
            final int numElements = (rightBound - leftBound) / neighboursDiff + 1;
            if (leftToRight) {
                leftBound += neighboursDiff;
                if (numElements % 2 != 0) {
                    rightBound -= neighboursDiff;
                }
            } else {
                rightBound -= neighboursDiff;
                if (numElements % 2 != 0) {
                    leftBound += neighboursDiff;
                }
            }
            leftToRight = !leftToRight;
            neighboursDiff = 2 * neighboursDiff;
            numPasses++;
        }
        return leftBound;
    }

    private int log2(final int n) {
        int res = 0;
        int pow = 1;
        while (pow < n) {
            res++;
            pow = 2 * pow;
        }
        return pow == n ? res : res - 1;
    }
}