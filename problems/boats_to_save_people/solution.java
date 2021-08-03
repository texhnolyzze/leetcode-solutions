class Solution {
    public int numRescueBoats(
        final int[] people,
        final int limit
    ) {
        Arrays.sort(people);
        int lo = 0;
        int hi = people.length - 1;
        int boats = 0;
        while (lo < hi) {
            final int loWeight = people[lo];
            final int hiWeight = people[hi];
            if (loWeight + hiWeight <= limit) {
                boats++;
                lo++;
                hi--;
            } else {
                boats++;
                hi--;
            }
        }
        if (lo == hi) {
            boats++;
        }
        return boats;
    }
}