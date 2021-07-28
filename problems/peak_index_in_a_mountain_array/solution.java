class Solution {
    public int peakIndexInMountainArray(final int[] arr) {
        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            final int len = r - l + 1;
            if (len <= 2) {
                return arr[l] < arr[r] ? r : l;
            }
            final int mid = l + (r - l) / 2;
            final int leftVal = arr[mid - 1];
            final int midVal = arr[mid];
            final int rightVal = arr[mid + 1];
            if (leftVal < midVal) {
                if (midVal > rightVal) {
                    return mid;
                } else {
                    l = mid + 1;
                }
            } else {
                r = mid - 1;
            }
        }
        throw new IllegalStateException();
    }
}