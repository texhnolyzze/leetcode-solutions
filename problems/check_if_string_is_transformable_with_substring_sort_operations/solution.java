class Solution {
    public boolean isTransformable(
        final String s,
        final String t
    ) {
        final int n = s.length();
        final FenwickTree tree = new FenwickTree(n);
        final List<Deque<Integer>> digitPositions = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            digitPositions.add(new LinkedList<>());
        }
        for (int i = 0; i < s.length(); i++) {
            digitPositions.get(
                s.charAt(i) - '0'
            ).add(i);
        }
        for (int i = 0; i < t.length(); i++) {
            final int target = t.charAt(i) - '0';
            final Deque<Integer> targetPositions = digitPositions.get(target);
            if (targetPositions.isEmpty()) {
                return false;
            }
            final int targetIdx = targetPositions.peek();
            final int targetActualIdx = tree.sumUntil(n - 1) - tree.sumUntil(targetIdx) + targetIdx;
            if (targetActualIdx != i) {
                for (int j = target - 1; j >= 0; j--) {
                    final Deque<Integer> lesserPositions = digitPositions.get(j);
                    if (lesserPositions.isEmpty()) {
                        continue;
                    }
                    final int lesserIdx = lesserPositions.peek();
                    final int lesserActualIdx = tree.sumUntil(n - 1) - tree.sumUntil(lesserIdx) + lesserIdx;
                    if (lesserActualIdx < targetActualIdx) {
                        return false;
                    }
                }
                tree.add(targetIdx);
            }
            targetPositions.remove();
        }
        return true;
    }

    static class FenwickTree {

        final int[] arr;

        public FenwickTree(final int n) {
            this.arr = new int[n + 1];
        }

        public void add(int i) {
            i++;
            while (i < arr.length) {
                arr[i]++;
                i += i & -i;
            }
        }

        public int sumUntil(int i) {
            i++;
            int res = 0;
            while (i > 0) {
                res += arr[i];
                i -= i & -i;
            }
            return res;
        }

    }
}