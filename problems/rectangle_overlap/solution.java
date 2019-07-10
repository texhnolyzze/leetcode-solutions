class Solution {
    public boolean isRectangleOverlap(int[] a, int[] b) {
        return overlaps(a[0], a[2], b[0], b[2]) && 
               overlaps(a[1], a[3], b[1], b[3]);
    }

    private static boolean overlaps(int a, int b, int c, int d) {
        return Math.min(b, d) > Math.max(a, c);
    }
}