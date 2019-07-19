class Solution {
    public int[][] intervalIntersection(int[][] a, int[][] b) {
        if (a.length == 0 || b.length == 0)
            return new int[0][];
        List<int[]> list = new LinkedList<>();
        int i = 0;
        int j = 0;
        while (true) {
            int[] intersection = intersection(a[i], b[j]);
            if (intersection != null)
                list.add(intersection);
            if (i + 1 < a.length) {
                if (j + 1 < b.length) {
                    if (a[i + 1][0] < b[j + 1][0])
                        i++;
                    else
                        j++;
                } else
                    i++;
            } else {
                if (j + 1 < b.length)
                    j++;
                else
                    break;
            }
        }
        int[][] ans = new int[list.size()][];
        int n = 0;
        for (int[] range : list)
            ans[n++] = range;
        return ans;
    }
    
    private static int[] intersection(int[] a, int[] b) {
        int maxOrigin = Math.max(a[0], b[0]);
        int minEnd = Math.min(a[1], b[1]);
        if (maxOrigin <= minEnd) {
            return new int[] {
                maxOrigin, minEnd
            };
        }
        return null;
    }
}