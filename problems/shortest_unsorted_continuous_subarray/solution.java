class Solution {
    public static int findUnsortedSubarray(int[] nums) {
        int[] minMax = new int[2];
        int shortest = nums.length;
        int i = 0, j = nums.length - 1; 
        while (true) {
            findMinMax(nums, i, j, minMax);
            if (nums[i] == minMax[0]) {
                do {
                    i++;
                    shortest--;
                } while (i <= j && nums[i] == minMax[0]);
            } else {
                if (nums[j] == minMax[1]) {
                    do {
                        j--;
                        shortest--;
                    } while (j >= i && nums[j] == minMax[1]);
                } else
                    return shortest;
            }
            if (i > j)
                return 0;
        }
    }
    
    private static void findMinMax(int[] arr, int from, int to, int[] store) {
        int min = arr[from], max = arr[from];
        for (int i = from + 1; i < to + 1; i++) {
            if (arr[i] < min) min = arr[i];
            else if (arr[i] > max) max = arr[i];
        }
        store[0] = min;
        store[1] = max;
    }
}