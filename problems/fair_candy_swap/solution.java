class Solution {
    public static int[] fairCandySwap(int[] a, int[] b) {
        boolean swapped = false;
        if (a.length > b.length) {
            int[] temp = a;
            a = b;
            b = temp;
            swapped = true;
        }
        Arrays.sort(a);
        int aSum = 0;
        int bSum = 0;
        for (int i = 0; i < a.length; i++) {
            aSum += a[i];
            bSum += b[i];
        }
        for (int i = a.length; i < b.length; i++)
            bSum += b[i];
        for (int i = 0; i < b.length; i++) {
            int dSum = (aSum + b[i]) - (bSum - b[i]);
            if (dSum % 2 != 0 || dSum <= 0)
                continue;
            int index = Arrays.binarySearch(a, dSum / 2);
            if (index >= 0) {
                return swapped ? new int[] {b[i], a[index]} : new int[] {a[index], b[i]};

            }
        }
        return null; // can't happen
    }
}