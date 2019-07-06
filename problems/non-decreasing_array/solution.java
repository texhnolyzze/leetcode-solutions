class Solution {
    public static boolean checkPossibility(int[] nums) {
        boolean canModifyFirst = true;
        for (int i = 2; i < nums.length; i++) {
            if (nums[i - 1] > nums[i]) {
                canModifyFirst = false;
                break;
            }
        }
        if (canModifyFirst)
            return true;
        boolean canModifyLast = true;
        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i - 1] > nums[i]) {
                canModifyLast = false;
                break;
            }
        }
        if (canModifyLast)
            return true;
        for (int i = 1; i < nums.length - 1; i++) {
            int elem = nums[i];
            int left = nums[i - 1], right = nums[i + 1];
            if ((elem > left && elem > right) || (elem < left && elem < right)) {
                boolean flag = true;
                for (int j = 1; j < i; j++) {
                    if (nums[j - 1] > nums[j]) {
                        flag = false;
                        break;
                    }
                }
                if (!flag)
                    continue;
                for (int j = i + 1, prevIdx = i - 1; j < nums.length; j++) {
                    if (nums[prevIdx] > nums[j]) {
                        flag = false;
                        break;
                    }
                    prevIdx = j;
                }
                if (flag)
                    return true;
            }
        }
        return false;
    }
}