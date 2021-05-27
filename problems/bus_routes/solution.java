class Solution {
    public int numBusesToDestination(
        final int[][] buses,
        final int source,
        final int target
    ) {
        if (source == target)
            return 0;
        final Map<Integer, Map<Integer, Integer>> stationBuses = new HashMap<>();
        for (int busId = 0; busId < buses.length; busId++) {
            final int[] route = buses[busId];
            for (int stationIdx = 0; stationIdx < route.length; stationIdx++) {
                final int stationId = route[stationIdx];
                stationBuses.computeIfAbsent(
                    stationId,
                    unused -> new HashMap<>()
                ).put(busId, stationIdx);
            }
        }
        final Set<Integer> visitedBuses = new HashSet<>();
        final Map<Integer, Set<Integer>> visitedStationsWithinBus = new HashMap<>();
        final PriorityQueue<BusRoutePosition> queue = new PriorityQueue<>(Comparator.comparingInt(position -> position.busTransitionsSoFar));
        for (final Integer busId : stationBuses.get(source).keySet()) {
            final BusRoutePosition pos = new BusRoutePosition(source, 1, busId);
            queue.add(pos);
            visitedStationsWithinBus.computeIfAbsent(
                busId,
                unused -> new HashSet<>()
            ).add(source);
            visitedBuses.add(busId);
        }
        while (!queue.isEmpty()) {
            final BusRoutePosition pos = queue.poll();
            if (pos.station == target) {
                return pos.busTransitionsSoFar;
            }
            final Map<Integer, Integer> busesFromStation = stationBuses.get(pos.station);
            for (final Map.Entry<Integer, Integer> bus : busesFromStation.entrySet()) {
                final int busId = bus.getKey();
                final int[] route = buses[busId];
                final int stationIdx = bus.getValue();
                final int nextStation = route[(stationIdx + 1) % route.length];
                if (pos.busId == busId) {
                    if (!visitedStationsWithinBus.get(busId).contains(nextStation)) {
                        queue.add(
                            new BusRoutePosition(
                                nextStation,
                                pos.busTransitionsSoFar,
                                busId
                            )
                        );
                        visitedStationsWithinBus.get(busId).add(nextStation);
                    }
                } else {
                    if (!visitedBuses.contains(busId)) {
                        visitedBuses.add(busId);
                        visitedStationsWithinBus.computeIfAbsent(busId, unused -> new HashSet<>()).add(nextStation);
                        queue.add(
                            new BusRoutePosition(
                                nextStation,
                                pos.busTransitionsSoFar + 1,
                                busId
                            )
                        );
                    }
                }
            }
        }
        return -1;
    }

    private static class BusRoutePosition {

        final int station;
        final int busTransitionsSoFar;
        final int busId;

        BusRoutePosition(
            final int station,
            final int busTransitionsSoFar,
            final int busId
        ) {
            this.station = station;
            this.busTransitionsSoFar = busTransitionsSoFar;
            this.busId = busId;
        }

        @Override
        @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
        public boolean equals(final Object o) {
            if (this == o) return true;
            final BusRoutePosition that = (BusRoutePosition) o;
            return station == that.station && Objects.equals(busId, that.busId);
        }

        @Override
        public int hashCode() {
            int result = station;
            result = 31 * result + busId;
            return result;
        }

    }
}