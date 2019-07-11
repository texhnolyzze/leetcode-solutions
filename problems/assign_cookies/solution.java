class Solution {
    public int findContentChildren(int[] childGreedFactor, int[] cookieSize) {
        if (childGreedFactor.length < 1 || cookieSize.length < 1)
            return 0;
        Arrays.sort(childGreedFactor);
        Arrays.sort(cookieSize);
        int maxContent = 0;
        for (int i = 0, cookieIndex = 0; i < childGreedFactor.length;) {
            if (childGreedFactor[i] <= cookieSize[cookieIndex]) {
                maxContent++;
                i++;
            } 
            cookieIndex++;
            if (cookieIndex == cookieSize.length)
                    break;
        }
        return maxContent;
    }
}