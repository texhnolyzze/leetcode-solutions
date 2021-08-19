import java.util.*;

class Solution {
    private final int leftIdx = 0;
    private final int rightIdx = 1;
    private final int heightIdx = 2;

    public List<List<Integer>> getSkyline(final int[][] buildings) {
        final List<List<Integer>> res = new LinkedList<>();
        final Collection<Integer> points = new TreeSet<>();
        for (final int[] building : buildings) {
            points.add(building[leftIdx]);
            points.add(building[rightIdx]);
        }
        for (final int point : points) {
            int h = 0;
            for (final int[] building : buildings) {
                final int left = building[leftIdx];
                final int right = building[rightIdx];
                if (left > point) {
                    break;
                } else if (point < right) {
                    h = Math.max(h, building[heightIdx]);
                }
            }
            if (res.isEmpty() || res.get(res.size() - 1).get(1) != h) {
                res.add(
                    List.of(
                        point,
                        h
                    )
                );
            }
        }
        return res;
    }
}