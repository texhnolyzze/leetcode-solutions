class Solution {
    public static int shipWithinDays(int[] weights, int days) {
        int left = 1, right = Arrays.stream(weights).sum() / days * 3;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canPackWithinDays(weights, days, mid)) 
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }
    
    private static boolean canPackWithinDays(int[] weights, int days, int weight) {
        int sum = 0;
        int numDays = 0;
        for (int i = 0; i < weights.length;) {
            int w = weights[i];
            if (sum + w > weight) {
                if (sum == 0)
                    return false;
                sum = 0;
                numDays++;
                continue;
            }
            i++;
            sum += w;
        }
        return numDays + 1 <= days;
    }
}