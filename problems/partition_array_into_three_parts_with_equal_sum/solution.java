class Solution {
    public static boolean canThreePartsEqualSum(int[] arr) {
        int[] prefixes = new int[arr.length];
        Map<Integer, List<Integer>> prefixSum = new HashMap<>();
        int sum = 0;
        for (int i = 0; i < arr.length - 2; i++) {
            sum += arr[i];
            prefixes[i] = sum;
            List<Integer> list = prefixSum.putIfAbsent(sum, new ArrayList<>());
            if (list == null) list = prefixSum.get(sum);
            list.add(i);
        }
        prefixes[arr.length - 2] = prefixes[arr.length - 3] + arr[arr.length - 2];
        prefixes[arr.length - 1] = prefixes[arr.length - 2] + arr[arr.length - 1];
        int suffixSum = 0;
        for (int i = arr.length - 1; i >= 2; i--) {
            suffixSum += arr[i];
            List<Integer> prefixesWithEqSum = prefixSum.get(suffixSum);
            if (prefixesWithEqSum != null) {
                for (int prefixEndIndex : prefixesWithEqSum) {
                    if (i - prefixEndIndex <= 1)
                        break;
                    int midSum = prefixes[i - 1] - prefixes[prefixEndIndex];
                    if (midSum == suffixSum)
                        return true;
                }
            }
        }
        return false;
    }
}