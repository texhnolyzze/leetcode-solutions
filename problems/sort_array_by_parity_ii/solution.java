class Solution {
    public int[] sortArrayByParityII(int[] arr) {
        int writeEvenTo = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 0) {
                int temp = arr[writeEvenTo];
                arr[writeEvenTo++] = arr[i];
                arr[i] = temp;
            } 
        }
        for (int i = 1, j = arr.length - 2; i < j; i += 2, j -= 2) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        return arr;
    }
}