class Solution {
    public int maximumUnits(
        final int[][] boxTypes,
        final int truckSize
    ) {
        final int numberOfBoxes = 0;
        final int unitsPerBox = 1;
        Arrays.sort(boxTypes, Comparator.comparingInt(type -> -type[unitsPerBox]));
        int res = 0;
        int inTruck = 0;
        for (final int[] type : boxTypes) {
            final int units = type[unitsPerBox];
            for (int i = 0; inTruck < truckSize && i < type[numberOfBoxes]; i++) {
                res += units;
                inTruck++;
            }
            if (inTruck == truckSize) {
                break;
            }
        }
        return res;
    }
}