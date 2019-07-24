class Solution {
    public int subarraySum(int[] nums, int k) {
        if (nums.length == 1)
            return nums[0] == k ? 1 : 0;
        Map<Integer, Integer> m = new HashMap<>();
        int sum = 0;
        int numSubarraysWithSumK = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            sum += num;
            if (sum == k)
                numSubarraysWithSumK++;
            numSubarraysWithSumK += (m.getOrDefault(k == 0 ? sum : sum - k, 0));
            m.merge(sum, 1, Integer::sum);
        }
        return numSubarraysWithSumK;
    }
}