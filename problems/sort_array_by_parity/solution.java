class Solution {
    public static int[] sortArrayByParity(int[] arr) {
        if (arr.length < 2)
            return arr;
        for (int i = 0, writeOddToIndex = arr.length - 1;;) {
            int num = arr[i];
            if (num % 2 != 0) 
                swap(arr, i, writeOddToIndex--);
            else i++;
            if (writeOddToIndex == i)
                break;
        }
        return arr;
    }
    
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
}