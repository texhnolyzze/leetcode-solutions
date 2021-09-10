class Solution {
    public int minMutation(
        final String start,
        final String end,
        final String[] bank
    ) {
        final boolean[][] graph = new boolean[bank.length + 2][bank.length + 2];
        final int geneStartIdx = 0;
        final int geneEndIdx = bank.length + 1;
        for (int i = 0; i < bank.length; i++) {
            final String variant = bank[i];
            graph[geneStartIdx][i + 1] = mutable(start, variant);
            graph[i + 1][geneEndIdx] = end.equals(variant);
            for (int j = i + 1; j < bank.length; j++) {
                graph[i + 1][j + 1] = graph[j + 1][i + 1] = mutable(variant, bank[j]);
            }
        }
        final Queue<MutationStep> queue = new LinkedList<>();
        final BitSet seen = new BitSet(bank.length + 2);
        seen.set(geneStartIdx);
        queue.add(new MutationStep(geneStartIdx, 0));
        while (!queue.isEmpty()) {
            final MutationStep curr = queue.poll();
            if (curr.geneVariantIdx == geneEndIdx) {
                return curr.mutationSequenceLength - 1;
            }
            final boolean[] adj = graph[curr.geneVariantIdx];
            for (int i = 0; i < adj.length; i++) {
                if (adj[i] && !seen.get(i)) {
                    seen.set(i);
                    queue.add(
                        new MutationStep(
                            i,
                            curr.mutationSequenceLength + 1
                        )
                    );
                }
            }
        }
        return -1;
    }

    private static class MutationStep {
        private final int geneVariantIdx;
        private final int mutationSequenceLength;
        private MutationStep(
            final int geneVariantIdx,
            final int mutationSequenceLength
        ) {
            this.geneVariantIdx = geneVariantIdx;
            this.mutationSequenceLength = mutationSequenceLength;
        }
    }

    private boolean mutable(final String from, final String to) {
        int dist = 0;
        for (int i = 0; i < from.length(); i++) {
            if (from.charAt(i) != to.charAt(i)) {
                dist++;
                if (dist > 1) {
                    return false;
                }
            }
        }
        return dist == 1;
    }
}