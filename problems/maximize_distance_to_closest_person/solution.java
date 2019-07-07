class Solution {
    public static int maxDistToClosest(int[] seats) {
        int max = 1;
        int left = -1;
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 1) {
                left = i;
                break;
            }
        }
        max = Math.max(max, left);
        int right = -1;
        for (int i = left + 1; i < seats.length; i++) {
            if (seats[i] == 1) {
                right = i;
                max = Math.max(max, (right - left) / 2);
                left = right;
            }
        }
        if (seats[seats.length - 1] == 0) {
            if (right != -1)
                max = Math.max(max, seats.length - right - 1);
            else
                max = Math.max(max, seats.length - left - 1);
        }
        return max;
    }
}