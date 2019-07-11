class Solution {
    public static List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < s.length();) {
            int groupSize = 1;
            char c = s.charAt(i++);
            while (i < s.length() && s.charAt(i) == c) {
                groupSize++;
                i++;
            }
            if (groupSize > 2)
                res.add(Arrays.asList(i - groupSize, i - 1));
        }
        return res;
    }
}