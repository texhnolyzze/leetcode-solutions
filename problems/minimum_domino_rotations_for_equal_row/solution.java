class Solution {
    public static int minDominoRotations(int[] a, int[] b) {
        int[] num = new int[6];
        for (int i = 0; i < a.length; i++) {
            num[a[i] - 1]++;
            num[b[i] - 1]++;
        }
        int minRotations = Integer.MAX_VALUE;
        outer: for (int domino = 1; domino <= 6; domino++) {
            if (num[domino - 1] < a.length)
                continue;
            int numARotations = 0;
            int numBRotations = 0;
            for (int i = 0; i < a.length; i++) {
                if (a[i] != domino) {
                    if (b[i] != domino)
                        continue outer;
                    numARotations++;
                } else {
                    if (b[i] != domino)
                        numBRotations++;
                }
            }
            minRotations = Math.min(minRotations, Math.min(numARotations, numBRotations));
        }
        return minRotations == Integer.MAX_VALUE ? -1 : minRotations;
    }
}