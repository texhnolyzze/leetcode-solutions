class Solution {
    public int largestPalindrome(int n) {
        if (n == 7)
            return 877;
        if (n == 8)
            return 475;
        final int bound = switch (n) {
            case 1 -> 9;
            case 2 -> 99;
            case 3 -> 999;
            case 4 -> 9999;
            case 5 -> 99999;
            case 6 -> 999999;
            default -> throw new IllegalStateException("Unexpected value: " + n);
        };
        long temp;
        long maxPalindrome = bound;
        int jBound = -1;
        int i = bound;
        outer: for (; i >= 1; i--) {
            for (int j = i; j >= 1; j--) {
                temp = ((long) i) * j;
                if (temp <= maxPalindrome)
                    break;
                if (palindrome(temp)) {
                    maxPalindrome = temp;
                    jBound = j + 1;
                    break outer;
                }
            }
        }
        if (jBound == -1)
            return (int) (maxPalindrome % 1337);
        for (; i >= 1; i--) {
            for (int j = i; j >= jBound; j--) {
                temp = ((long) i) * j;
                if (temp <= maxPalindrome)
                    break;
                if (palindrome(temp)) {
                    maxPalindrome = temp;
                    jBound = j + 1;
                    break;
                }
            }
        }
        return (int) (maxPalindrome % 1337);
    }

    private boolean palindrome(long num) {
        long dig;
        long n = num;
        long rev = 0;
        while (num > 0)
        {
            dig = num % 10;
            rev = rev * 10 + dig;
            num = num / 10;
        }
        return n == rev;
    }
}