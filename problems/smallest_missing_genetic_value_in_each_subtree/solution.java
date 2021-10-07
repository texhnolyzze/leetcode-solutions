class Solution {
    
    public int[] smallestMissingValueSubtree(
        final int[] parents,
        final int[] geneticValue
    ) {
        final Map<Integer, List<Integer>> childrenOf = new HashMap<>();
        final int n = parents.length;
        int oneIdx = geneticValue[0] == 1 ? 0 : -1;
        for (int i = 1; i < parents.length; i++) {
            if (geneticValue[i] == 1) {
                oneIdx = i;
            }
            childrenOf.computeIfAbsent(
                parents[i],
                unused -> new ArrayList<>(1)
            ).add(i);
        }
        final int[] res = new int[n];
        if (oneIdx != -1) {
            final DisjointSet subtrees = new DisjointSet(100002);
            int curr = oneIdx;
            do {
                smallestMissingValueSubtree(
                    childrenOf,
                    geneticValue,
                    subtrees,
                    curr,
                    res
                );
                curr = parents[curr];
            } while (curr != -1);
            for (int i = 0; i < res.length; i++) {
                if (res[i] == 0) {
                    res[i] = 1;
                }
            }
        } else {
            Arrays.fill(res, 1);
        }
        return res;
    }

    private void smallestMissingValueSubtree(
        final Map<Integer, List<Integer>> childrenOf,
        final int[] geneticValue,
        final DisjointSet subtrees,
        final int rootIdx,
        final int[] res
    ) {
        final List<Integer> children = childrenOf.getOrDefault(rootIdx, Collections.emptyList());
        if (children.isEmpty()) {
            res[rootIdx] = geneticValue[rootIdx] == 1 ? 2 : 1;
        } else {
            final int myGeneticValue = geneticValue[rootIdx];
            for (int i = 0; i < children.size(); i++) {
                final int child = children.get(i);
                if (res[child] == 0) {
                    smallestMissingValueSubtree(
                        childrenOf,
                        geneticValue,
                        subtrees,
                        child,
                        res
                    );
                }
                subtrees.union(
                    myGeneticValue,
                    geneticValue[child]
                );
            }
            int missing = -1;
            for (int i = 0; i < children.size(); i++) {
                final int child = children.get(i);
                if (res[child] != 1) {
                    missing = res[child];
                    break;
                }
            }
            if (missing == -1 && myGeneticValue == 1) {
                missing = 2;
            }
            if (missing == -1) {
                missing = 1;
            } else {
                final int mySubset = subtrees.find(myGeneticValue);
                for (;missing < 100002; missing++) {
                    final int missingSubset = subtrees.find(missing);
                    if (missingSubset != mySubset) {
                        break;
                    }
                }
            }
            res[rootIdx] = missing;
        }
    }
    
    static class DisjointSet {

        private final int[] parent;
        private final int[] rank;

        public DisjointSet(final int size) {
            this.parent = new int[size];
            this.rank = new int[size];
            for (int i = 0; i < size; ++i) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        public int find(final int x) {
            if (x == parent[x]) {
                return x;
            }
            return parent[x] = find(parent[x]);
        }

        public void union(
            final int x,
            final int y
        ) {
            final int px = find(x);
            final int py = find(y);
            if (rank[px] < rank[py]) {
                parent[px] = py;
            } else {
                parent[py] = px;
            }
            if (rank[px] == rank[py]) {
                ++rank[px];
            }
        }

    }
    
}