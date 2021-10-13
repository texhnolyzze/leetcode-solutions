class Solution {
    public int findTheCity(
        final int n,
        final int[][] edges,
        final int distanceThreshold
    ) {
        final int fromIdx = 0;
        final int toIdx = 1;
        final int weightIdx = 2;
        final int[][] graph = new int[n][n];
        for (final int[] edge : edges) {
            graph[edge[fromIdx]][edge[toIdx]] = edge[weightIdx];
            graph[edge[toIdx]][edge[fromIdx]] = edge[weightIdx];
        }
        final int[] numPathsWithDistanceAtMostThreshold = new int[n];
        final int[] dist = new int[n];
        final BitSet marked = new BitSet(n);
        for (int source = 0; source < n; source++) {
            Arrays.fill(dist, Integer.MAX_VALUE);
            final TreeSet<Integer> queue = new TreeSet<>(
                (v1, v2) -> {
                    final int cmp = Integer.compare(dist[v1], dist[v2]);
                    if (cmp == 0) {
                        return Integer.compare(v1, v2);
                    }
                    return cmp;
                }
            );
            dist[source] = 0;
            queue.add(source);
            while (!queue.isEmpty()) {
                final int curr = queue.pollFirst();
                marked.set(curr);
                final int d = dist[curr];
                final int[] adj = graph[curr];
                for (int neighbour = 0; neighbour < n; neighbour++) {
                    if (neighbour == curr || marked.get(neighbour)) {
                        continue;
                    }
                    final int weight = adj[neighbour];
                    if (weight == 0) {
                        continue;
                    }
                    if (d + weight < dist[neighbour]) {
                        queue.remove(neighbour);
                        dist[neighbour] = d + weight;
                        queue.add(neighbour);
                    }
                }
            }
            for (int target = 0; target < dist.length; target++) {
                if (source == target) {
                    continue;
                }
                final int d = dist[target];
                if (d > 0 && d <= distanceThreshold) {
                    numPathsWithDistanceAtMostThreshold[source]++;
                }
            }
            if (source != n - 1) {
                marked.clear(0, n);
            }
        }
        int minIdx = 0;
        int min = numPathsWithDistanceAtMostThreshold[minIdx];
        for (int i = 1; i < n; i++) {
            final int candidate = numPathsWithDistanceAtMostThreshold[i];
            if (candidate <= min) {
                min = candidate;
                minIdx = i;
            }
        }
        return minIdx;
    }
}