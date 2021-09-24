class Solution {
    public int leastBricks(final List<List<Integer>> wall) {
        final int rows = wall.size();
        final Map<Long, Integer> count = new HashMap<>(rows);
        int min = rows;
        for (int i = 0; i < wall.size(); i++) {
            final List<Integer> row = wall.get(i);
            long offset = 0;
            for (int j = 0; j < row.size() - 1; j++) {
                final int brick = row.get(j);
                offset += brick;
                min = Math.min(
                    min,
                    rows - count.compute(offset, (unused, cnt) -> cnt == null ? 1 : cnt + 1)
                );
            }
        }
        return min;
    }
}