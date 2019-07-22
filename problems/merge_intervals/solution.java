class Solution {
    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[0], i2[0]));
        Deque<int[]> stack = new LinkedList<>();
        for (int[] i1 : intervals) {
            if (!stack.isEmpty()) {
                int[] merged = i1;
                while (!stack.isEmpty()) {
                    int[] i2 = stack.peek();
                    int[] temp = merge(merged, i2);
                    if (temp != null) {
                        merged = temp;
                        stack.pop();
                    } else
                        break;
                }
                stack.push(merged);
            } else
                stack.push(i1);
        }
        return stack.toArray(new int[stack.size()][]);
    }
    
    private static int[] merge(int[] a, int[] b) {
        int maxOrigin = Math.max(a[0], b[0]);
        int minEnd = Math.min(a[1], b[1]);
        if (maxOrigin <= minEnd) {
            return new int[] {
                Math.min(a[0], b[0]),
                Math.max(a[1], b[1])
            };
        }
        return null;
    }
}