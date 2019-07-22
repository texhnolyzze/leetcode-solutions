class Solution {
    public static int trap(int[] height) {
        int res = 0;
        Deque<int[]> trappedStack = new LinkedList<>();
        for (int i = 2; i < height.length; i++) {
            int hi = height[i];
            int maxPrevHeight = Integer.MIN_VALUE;
            int maxPrevHeightIndex = -1;
            for (int j = i - 1; j >= 0; j--) {
                int hj = height[j];
                if (hj > maxPrevHeight) {
                    maxPrevHeight = hj;
                    maxPrevHeightIndex = j;
                    if (hj >= hi)
                        break;
                }
            }
            int h = Math.min(hi, maxPrevHeight);
            if (i - maxPrevHeightIndex > 1) {
                while (!trappedStack.isEmpty() && trappedStack.peek()[1] > maxPrevHeightIndex) {
                    res -= trappedStack.pop()[0];
                }
                for (int k = maxPrevHeightIndex + 1; k < i; k++) {
                    trappedStack.push(new int[] {h - height[k], k});
                    res += (h - height[k]);
                }
            }
        }
        return res;
    }
}