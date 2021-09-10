class Solution {
    public int addRungs(
        final int[] rungs,
        final int dist
    ) {
        int rungsAdded = 0;
        int height = 0;
        for (final int rung : rungs) {
            if (height + dist < rung) {
                final int diff = rung - height;
                rungsAdded += diff % dist == 0 ?
                              diff / dist - 1 :
                              diff / dist;
            }
            height = rung;
        }
        return rungsAdded;
    }
}