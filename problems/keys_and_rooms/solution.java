class Solution {
    public boolean canVisitAllRooms(final List<List<Integer>> rooms) {
        final BitSet visited = new BitSet(rooms.size());
        visitRoom(rooms, visited, 0);
        return visited.cardinality() == rooms.size();
    }

    private void visitRoom(
        final List<List<Integer>> rooms,
        final BitSet visited,
        final int idx
    ) {
        visited.set(idx);
        final List<Integer> keys = rooms.get(idx);
        for (int i = 0, len = keys.size(); i < len; i++) {
            final int roomIdx = keys.get(i);
            if (!visited.get(roomIdx)) {
                visitRoom(rooms, visited, roomIdx);
            }
        }
    }
}