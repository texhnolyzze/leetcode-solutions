class Solution {
    public int[] createTargetArray(int[] nums, int[] index) {
        List<Integer> res = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            res.add(index[i], nums[i]);
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }
}