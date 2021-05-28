class Solution {
    public int numSimilarGroups(final String[] strs) {
        DisjointSet set = new DisjointSet(strs.length);
        for (int id = 0; id < strs.length; id++) {
            set.makeSet(id);
        }
        for (int i = 0; i < strs.length; i++) {
            for (int j = i + 1; j < strs.length; j++) {
                if (similar(strs[i], strs[j]))
                    set.union(i, j);
            }
        }
        return set.size();
    }

    private boolean similar(final String s1, final String s2) {
        int dist = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                dist++;
            }
            if (dist > 2)
                return false;
        }
        return dist == 2 || dist == 0;
    }

    static class DisjointSet {

        private final int[] tree;

        DisjointSet(final int size) {
            this.tree = new int[size];
            Arrays.fill(tree, -1);
        }

        void makeSet(int v) {
            tree[v] = v;
        }

        void union(int v1, int v2) {
            int v1Root = v1;
            while (tree[v1Root] != v1Root) {
                v1Root = tree[v1Root];
            }
            int v2Root = v2;
            while (tree[v2Root] != v2Root) {
                int temp = tree[v2Root];
                tree[v2Root] = v1Root;
                v2Root = temp;
            }
            tree[v2Root] = v1Root;
        }

        public int size() {
            int size = 0;
            for (int i = 0; i < tree.length; i++) {
                if (tree[i] == i)
                    size++;
            }
            return size;
        }

    }
}