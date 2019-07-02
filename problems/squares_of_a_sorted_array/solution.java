class Solution {
    public static int[] sortedSquares(int[] arr) {
        int sizeOfNegativeSubArray = 0;
        for (int i = 0; i < arr.length && arr[i] < 0; i++) 
            sizeOfNegativeSubArray++;
        if (sizeOfNegativeSubArray == 0) {
            for (int i = 0; i < arr.length; i++)
                arr[i] = arr[i] * arr[i];
            return arr;
        } 
        if (sizeOfNegativeSubArray == arr.length) {
            for (int i = 0, j = arr.length - 1; i <= j; i++, j--) {
                int temp = arr[i] * arr[i];
                arr[i] = arr[j] * arr[j];
                arr[j] = temp;
            }
        }
        for (int i = 0, j = sizeOfNegativeSubArray - 1; i <= j; i++, j--) {
            int temp = arr[i] * arr[i];
            arr[i] = arr[j] * arr[j];
            arr[j] = temp;
        }
        for (int i = sizeOfNegativeSubArray; i < arr.length; i++) 
            arr[i] = arr[i] * arr[i];
        int[] outputArray = new int[arr.length];
        int i = 0, j = sizeOfNegativeSubArray, k = 0;
        while (i < sizeOfNegativeSubArray && j < arr.length) {
            if (arr[i] < arr[j]) outputArray[k++] = arr[i++];
            else outputArray[k++] = arr[j++];
        }
        while (i < sizeOfNegativeSubArray) outputArray[k++] = arr[i++];
        while (j < arr.length) outputArray[k++] = arr[j++];
        return outputArray;
    }
}