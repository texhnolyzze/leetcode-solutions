class Solution {
    public int arrangeCoins(int n) {
        long ln = -n;
        double x1 = (-1 + Math.sqrt(1 - 8 * ln)) / 2.0; 
        return (int) x1;
    }
}