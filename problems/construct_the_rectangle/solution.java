class Solution {
    public static int[] constructRectangle(int area) {
        int optimalWidth = 1;
        int optimalLen = area;
        int optimalDiff = optimalLen - optimalWidth;
        double areaAsDouble = area;
        for (int i = 2; i <= Math.sqrt(area); i++) {
            if ((areaAsDouble / i) % 1 == 0.0) {
                int newLen = area / i;
                int diff = newLen - i;
                if (diff < 0)
                    break;
                if (diff < optimalDiff) {
                    optimalWidth = i;
                    optimalLen = newLen;
                    optimalDiff = diff;
                }
            }
        }
        return new int[] {optimalLen, optimalWidth};
    }
}