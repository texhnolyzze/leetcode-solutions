class Solution {
    public int maxHappyGroups(int batchSize, int[] groups) {
        GroupSequence result = Arrays.stream(
            solve(
                batchSize,
                groups,
                groups.length
            )
        ).flatMap(
            Arrays::stream
        ).filter(
            Objects::nonNull
        ).max(
            Comparator.comparingInt(
                sequence -> sequence.numHappyGroups
            )
        ).orElseThrow();
        return result.numHappyGroups;
    }

    private GroupSequence[][] solve(
        final int batchSize,
        final int[] groups,
        final int numGroups
    ) {
        GroupSequence[][] result = new GroupSequence[batchSize][batchSize];
        if (numGroups == 1) {
            for (int i = 0; i < groups.length; i++) {
                final int groupSize = groups[i];
                final int donutsRemained = (batchSize - (groupSize % batchSize)) % batchSize;
                if (result[0][donutsRemained] == null) {
                    GroupSequence sequence = new GroupSequence(
                        Collections.singletonList(i),
                        1
                    );
                    result[0][donutsRemained] = sequence;
                }
            }
        } else {
            GroupSequence[][] prevSeqTable = solve(batchSize, groups, numGroups - 1);
            for (int i = 0; i < groups.length; i++) {
                for (final GroupSequence[] prevSequences : prevSeqTable) {
                    for (
                        int donutsRemainedFromPreviousGroup = batchSize - 1;
                        donutsRemainedFromPreviousGroup >= 0;
                        donutsRemainedFromPreviousGroup--
                    ) {
                        GroupSequence prevSequence = prevSequences[donutsRemainedFromPreviousGroup];
                        if (prevSequence == null)
                            continue;
                        if (prevSequence.groupIds.contains(i))
                            continue;
                        final int groupSize = groups[i];
                        final boolean groupHappy = donutsRemainedFromPreviousGroup == 0;
                        final int donutsRemained =
                            donutsRemainedFromPreviousGroup >= groupSize ?
                            donutsRemainedFromPreviousGroup - groupSize :
                            (batchSize - (groupSize - donutsRemainedFromPreviousGroup) % batchSize) % batchSize;
                        final int numHappyGroups = prevSequence.numHappyGroups + (groupHappy ? 1 : 0);
                        if (
                            result[donutsRemainedFromPreviousGroup][donutsRemained] == null ||
                            result[donutsRemainedFromPreviousGroup][donutsRemained].numHappyGroups < numHappyGroups
                        ) {
                            ArrayList<Integer> newSequenceGroupIds = new ArrayList<>(prevSequence.groupIds);
                            newSequenceGroupIds.add(i);
                            GroupSequence candidateSequence = new GroupSequence(
                                newSequenceGroupIds,
                                numHappyGroups
                            );
                            result[donutsRemainedFromPreviousGroup][donutsRemained] = candidateSequence;
                        }
                    }
                }
            }
        }
        return result;
    }

    private static class GroupSequence {

        final List<Integer> groupIds;
        final int numHappyGroups;

        private GroupSequence(
            final List<Integer> groupIds,
            final int numHappyGroups
        ) {
            this.groupIds = groupIds;
            this.numHappyGroups = numHappyGroups;
        }

    }
}