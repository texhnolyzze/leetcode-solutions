class Solution {
    public int minSubarray(
        final int[] nums,
        final int p
    ) {
        final Map<Integer, TreeSet<Integer>> prefixMap = new HashMap<>();
        int sum = 0;
        final int total;
        prefixMap.computeIfAbsent(0, unused -> new TreeSet<>()).add(-1);
        for (int i = 0; i < nums.length; i++) {
            sum = (sum + nums[i]) % p;
            prefixMap.computeIfAbsent(sum, unused -> new TreeSet<>()).add(i);
        }
        total = sum;
        int min = Integer.MAX_VALUE;
        sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = (sum + nums[i]) % p;
            final int target = (p - total + sum) % p;
            final TreeSet<Integer> set = prefixMap.get(target);
            if (set != null) {
                final Integer least = set.floor(i);
                if (least != null) {
                    min = Math.min(min, i - least);
                }
            }
        }
        return min == Integer.MAX_VALUE || min == nums.length ? -1 : min;
    }
}