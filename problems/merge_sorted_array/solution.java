class Solution {
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0)
            return;
        if (m == 0) {
            for (int i = 0; i < n; i++) 
                nums1[i] = nums2[i];
        }
        int i = 0, j = 0, k = n + m - 1;
        int rightBound = m - 1;
        int remainFromNums1 = m;
        while (true) {
            if (nums1[i] < nums2[j]) {
                nums1[k--] = nums1[i++];
                remainFromNums1--;
            } else nums1[k--] = nums2[j++];
            if (remainFromNums1 == 0 || j == n)
                break;
            if (k == rightBound) {
                int shiftToLeftCount = remainFromNums1;
                for (int l = 0; shiftToLeftCount != 0; shiftToLeftCount--) 
                    nums1[l++] = nums1[i++];
                i = 0;
                rightBound = remainFromNums1 - 1;
            }
        }
        if (remainFromNums1 != 0) {
            for (i = 0, j = remainFromNums1 - 1; i < j; i++, j--) {
                int temp = nums1[i];
                nums1[i] = nums1[j];
                nums1[j] = temp;
            }
        } else if (j < n) {
            do {
                nums1[k--] = nums2[j++];
            } while (k >= 0);
        }
        for (i = 0, j = m + n - 1; i < j; i++, j--) {
            int temp = nums1[i];
            nums1[i] = nums1[j];
            nums1[j] = temp;
        }
    }
}