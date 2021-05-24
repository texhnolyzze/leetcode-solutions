class Solution {
    public int maxAscendingSum(int[] arr) {
        int sum = arr[0];
        int max = -1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] <= arr[i - 1]) {
                max = Math.max(max, sum);
                sum = arr[i];
            } else {
                sum += arr[i];
            }
        }
        max = Math.max(max, sum);
        return max;
    }
}