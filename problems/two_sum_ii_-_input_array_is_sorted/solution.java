class Solution {
    public int[] twoSum(int[] numbers, int target) {
        for (int i = 1; i < numbers.length; i++) {
            int j = Arrays.binarySearch(numbers, 0, i, target - numbers[i]);
            if (j >= 0)
                return new int[] {j + 1, i + 1};
        }
        return null; // can't happen
    }
}