class Solution {
    public int computeArea(
        final int ax1,
        final int ay1,
        final int ax2,
        final int ay2,
        final int bx1,
        final int by1,
        final int bx2,
        final int by2
    ) {
        final int area1 = (ax2 - ax1) * (ay2 - ay1);
        final int area2 = (bx2 - bx1) * (by2 - by1);
        final int xStartMax = Math.max(ax1, bx1);
        final int xEndMin = Math.min(ax2, bx2);
        if (xStartMax >= xEndMin) {
            return area1 + area2;
        }
        final int yStartMax = Math.max(ay1, by1);
        final int yEndMin = Math.min(ay2, by2);
        if (yStartMax >= yEndMin) {
            return area1 + area2;
        }
        final int xSide = xEndMin - xStartMax;
        final int ySide = yEndMin - yStartMax;
        return area1 + area2 - xSide * ySide;
    }
}