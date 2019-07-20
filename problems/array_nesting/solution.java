class Solution {
    public static int arrayNesting(int[] nums) {
        int longestSet = 0;
        boolean[] visited = new boolean[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int j = i;
            int size = 0;
            while (true) {
                if (visited[j])
                    break;
                size++;
                visited[j] = true;
                j = nums[j];
            }
            longestSet = Math.max(longestSet, size);
        }
        return longestSet;
    }
}