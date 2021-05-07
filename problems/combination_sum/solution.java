class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        int[] maxOccurs = new int[candidates.length];
        for (int i = 0; i < candidates.length; i++) {
            maxOccurs[i] = target / candidates[i];
        }
        return solve(candidates, 0, target, maxOccurs, 0, new LinkedList<>(), new LinkedList<>());
    }

    private List<List<Integer>> solve(
        final int[] candidates,
        final int candidateIdx,
        final int target,
        final int[] maxOccurs,
        final int sum,
        final List<List<Integer>> results,
        final List<Integer> currentCandidateSequence
    ) {
        if (sum == target) {
            results.add(currentCandidateSequence);
            return results;
        }
        if (sum + candidates[candidateIdx] > target) {
            return results;
        } else if (sum + candidates[candidateIdx] == target) {
            currentCandidateSequence.add(candidates[candidateIdx]);
            results.add(currentCandidateSequence);
            return results;
        } else {
            for (int i = 0; i <= maxOccurs[candidateIdx]; i++) {
                if (candidateIdx + 1 < candidates.length) {
                    LinkedList<Integer> copy = new LinkedList<>(currentCandidateSequence);
                    if (i != 0) {
                        for (int j = 0; j < i; j++) {
                            copy.add(candidates[candidateIdx]);
                        }
                    }
                    solve(
                        candidates,
                        candidateIdx + 1,
                        target,
                        maxOccurs,
                        sum + candidates[candidateIdx] * i,
                        results,
                        copy
                    );
                } else {
                    boolean eq = sum + candidates[candidateIdx] * i == target;
                    if (eq) {
                        for (int j = 0; j < i; j++) {
                            currentCandidateSequence.add(candidates[candidateIdx]);
                        }
                        results.add(currentCandidateSequence);
                        break;
                    }
                }
            }
            return results;
        }
    }
}