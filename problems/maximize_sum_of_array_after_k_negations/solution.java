class Solution {
    public int largestSumAfterKNegations(int[] arr, int k) {
        Arrays.sort(arr);
        int sum = 0;
        for (int elem : arr) 
            sum += elem;
        int negatesRemained = k;
        for (int i = 0; i < arr.length && negatesRemained != 0; i++) {
            if (arr[i] < 0) {
                sum += 2 * (-arr[i]);
                arr[i] = -arr[i];
                negatesRemained--;
            } else if (arr[i] == 0) {
                return sum;
            } else
                break;
        }
        if (negatesRemained % 2 == 0)
            return sum;
        else {
            int min = arr[0];
            for (int i = 1; i < arr.length; i++)
                if (arr[i] < min) 
                    min = arr[i];
            return sum - 2 * min;
        }
    }
}