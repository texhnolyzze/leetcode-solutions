class Solution {
    public boolean isPowerOfFour(int n) {
        return Integer.bitCount(n) == 1 && (n & 0b010101010101010101010101010101010) == 0;
    }
}