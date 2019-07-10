class Solution {
public static int maximumProduct(int[] nums) {
        if (nums.length < 3)
            return 0;
        int[] maxNegativePair = new int[2];
        for (int i = 0; i < 3; i++) {
            if (nums[i] < 0) {
                if (maxNegativePair[1] > nums[i]) {
                    maxNegativePair[0] = maxNegativePair[1];
                    maxNegativePair[1] = nums[i];
                } else if (maxNegativePair[0] > nums[i])
                    maxNegativePair[0] = nums[i];
            } 
        }
        int[] temp = new int[3];
        temp[0] = nums[0]; temp[1] = nums[1]; temp[2] = nums[2];
        Arrays.sort(temp);
        for (int i = 3; i < nums.length; i++) {
            if (nums[i] < 0) {
                if (maxNegativePair[1] > nums[i]) {
                    maxNegativePair[0] = maxNegativePair[1];
                    maxNegativePair[1] = nums[i];
                } else if (maxNegativePair[0] > nums[i])
                    maxNegativePair[0] = nums[i];
            } 
            if (temp[2] < nums[i]) {
                temp[0] = temp[1];
                temp[1] = temp[2];
                temp[2] = nums[i];
            } else if (temp[1] < nums[i]) {
                temp[0] = temp[1];
                temp[1] = nums[i];
            } else if (temp[0] < nums[i])
                temp[0] = nums[i];
        }
        int mul1 = maxNegativePair[0] * maxNegativePair[1] * temp[2];
        int mul2 = temp[0] * temp[1] * temp[2];
        return Math.max(mul1, mul2);
    }
}