class Solution {
    public int findNumberOfLIS(final int[] nums) {
        final int[] longestIncreasingSubsequence = new int[nums.length];
        final int[] countLongestIncreasingSubsequence = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            longestIncreasingSubsequence[i] = longestIncreasingSubsequence(
                i,
                nums,
                longestIncreasingSubsequence,
                countLongestIncreasingSubsequence
            );
        }
        int count = countLongestIncreasingSubsequence[0];
        int maxLen = longestIncreasingSubsequence[0];
        for (int i = 1; i < longestIncreasingSubsequence.length; i++) {
            final int len = longestIncreasingSubsequence[i];
            if (len > maxLen) {
                maxLen = len;
                count = countLongestIncreasingSubsequence[i];
            } else if (len == maxLen) {
                count += countLongestIncreasingSubsequence[i];
            }
        }
        return count;
    }

    private int longestIncreasingSubsequence(
        final int start,
        final int[] nums,
        final int[] longestIncreasingSubsequence,
        final int[] countLongestIncreasingSubsequence
    ) {
        int res = longestIncreasingSubsequence[start];
        if (res != 0) {
            return res;
        }
        res = 1;
        int count = 1;
        for (int i = start + 1; i < nums.length; i++) {
            if (nums[i] > nums[start]) {
                final int nextLongest = 1 + longestIncreasingSubsequence(
                    i,
                    nums,
                    longestIncreasingSubsequence,
                    countLongestIncreasingSubsequence
                );
                if (nextLongest > res) {
                    res = nextLongest;
                    count = countLongestIncreasingSubsequence[i];
                } else if (nextLongest == res) {
                    count += countLongestIncreasingSubsequence[i];
                }
            }
        }
        longestIncreasingSubsequence[start] = res;
        countLongestIncreasingSubsequence[start] = count;
        return res;
    }
}