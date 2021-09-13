class Solution {
    public int maxDistance(
        final int[] nums1,
        final int[] nums2
    ) {
        int max = 0;
        int i = 0;
        int j = 0;
        while (i < nums1.length && j < nums2.length) {
            final int num1 = nums1[i];
            final int num2 = nums2[j];
            if (num1 <= num2) {
                max = Math.max(max, j - i);
                j++;
            } else {
                i++;
                if (j < i) {
                    j = i;
                }
            }
        }
        return max;
    }
}