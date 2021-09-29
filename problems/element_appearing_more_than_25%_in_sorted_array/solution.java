class Solution {
    public int findSpecialInteger(final int[] arr) {
        Arrays.sort(arr);
        int longestSequenceLen = 1;
        int mostFrequent = arr[0];
        int currSequenceLen = 1;
        int currElem = arr[0];
        for (int i = 1; i < arr.length; i++) {
            final int n = arr[i];
            if (n == currElem) {
                currSequenceLen++;
            } else {
                if (currSequenceLen > longestSequenceLen) {
                    longestSequenceLen = currSequenceLen;
                    mostFrequent = currElem;
                }
                currSequenceLen = 1;
                currElem = n;
            }
        }
        if (currSequenceLen > longestSequenceLen) {
            mostFrequent = currElem;
        }
        return mostFrequent;
    }
}