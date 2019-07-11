class Solution {
    public int findJudge(int n, int[][] trust) {
        boolean[] trustsAnyone = new boolean[n];
        int[] amountOfTrustedPeople = new int[n];
        for (int[] trust1 : trust) {
            int trusting = trust1[0] - 1;
            int trusted = trust1[1] - 1;
            trustsAnyone[trusting] = true;
            amountOfTrustedPeople[trusted]++;
        }
        for (int i = 0; i < n; i++) {
            if (amountOfTrustedPeople[i] == n - 1 && !trustsAnyone[i]) 
                return i + 1;
        }
        return -1;
    }
}