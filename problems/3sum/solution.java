class Solution {
    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        Set<List<Integer>> set = new HashSet<>();
        for (int i = 2; i < nums.length; i++) {
            int a = nums[i];
            for (int j = i - 1; j >= 1; j--) {
                int b = nums[j];
                int c = -a - b;
                if (Arrays.binarySearch(nums, 0, j, c) >= 0) {
                    List<Integer> triple = new ArrayList<>(3);
                    triple.add(a);
                    triple.add(b);
                    triple.add(c);
                    triple.sort(Integer::compare);
                    set.add(triple);
                }
            }
        }
        return new ArrayList<>(set);
    }
}