class Solution {
    public int minDeletionSize(String[] a) {
        int dSize = 0;
        int m = a[0].length();
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < a.length; j++) {
                if (a[j].charAt(i) < a[j - 1].charAt(i)) {
                    dSize++;
                    break;
                }
            }
        }
        return dSize;
    }
}