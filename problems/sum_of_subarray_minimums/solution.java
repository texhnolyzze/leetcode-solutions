class Solution {
    
    private static final int MOD = 1000000007;

    public int sumSubarrayMins(final int[] arr) {
        final int[] left = new int[arr.length];
        final int[] right = new int[arr.length];
        final Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            final int curr = arr[i];
            while (!stack.isEmpty() && arr[stack.peek()] > curr) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
            stack.push(i);
        }
        stack.clear();
        for (int i = arr.length - 1; i >= 0; i--) {
            final int curr = arr[i];
            while (!stack.isEmpty() && arr[stack.peek()] >= curr) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? arr.length - i : stack.peek() - i;
            stack.push(i);
        }
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            final long temp = (long) arr[i] * left[i] % MOD * right[i] % MOD;
            sum = (int) ((sum + temp) % MOD);
        }
        return sum;
    }
    
}