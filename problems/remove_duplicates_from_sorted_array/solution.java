import java.util.Arrays;
import java.util.function.IntConsumer;

class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0)
            return 0;
        int n = 0;
        for (int i = 0, idx = 0; i < nums.length; idx++) {
            int curr = nums[i];
            int numCurrElement = 1;
            for (int j = i + 1; j < nums.length && nums[j] == curr; j++) 
                numCurrElement++;
            nums[idx] = curr;
            i += numCurrElement;
            n++;
        }
        return n;
    }
}