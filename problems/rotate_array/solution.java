class Solution {
    
    public static void rotate(int[] nums, int k) {
        rotate(nums, 0, k);
    }
    
    private static void rotate(int[] nums, int from, int k) {
        int to = nums.length - 1;
        int n = to - from + 1;
        if (n <= 1)
            return;
        k = k % n;
        if (k == 0)
            return;
        for (int i = from, j = to - k + 1; i < from + k; i++, j++) 
            swap(nums, i, j);
        if (from + k - 1 >= to - k + 1) {
            rotate(nums, from + k, (from + k - 1) - (to - k + 1) + 1);
            return;
        }
        int count = 0;
        for (int i = from + k, j = to - k + 1; count < k && i < to - k + 1; i++, j++, count++) 
            swap(nums, i, j);
        int p = (to - k) - (from + k) + 1;
        if (count == k)
            rotate(nums, nums.length - p, k);
        else
            rotate(nums, nums.length - k, k - p);
    }
    
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
}