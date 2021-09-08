class Solution {
    public boolean validateStackSequences(
        final int[] pushed,
        final int[] popped
    ) {
        final Deque<Integer> stack = new LinkedList<>();
        int pushedIdx = 0;
        int poppedIdx = 0;
        while (poppedIdx < popped.length) {
            final int expectedPop = popped[poppedIdx++];
            if (!stack.isEmpty() && stack.peek() == expectedPop) {
                stack.pop();
                continue;
            }
            while (pushedIdx < pushed.length) {
                final int pushedElem = pushed[pushedIdx++];
                stack.push(pushedElem);
                if (pushedElem == expectedPop) {
                    break;
                }
            }
            if (stack.isEmpty() || stack.pop() != expectedPop) {
                return false;
            }
        }
        return true;
    }
}