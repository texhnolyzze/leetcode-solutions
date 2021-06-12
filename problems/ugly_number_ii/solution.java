class Solution {
    public int nthUglyNumber(final int n) {
        final TreeSet<Long> nums = new TreeSet<>(Comparator.naturalOrder());
        nums.add(1L);
        int pos = 1;
        while (true) {
            final long next = nums.first();
            if (pos++ == n)
                return (int) next;
            nums.remove(next);
            nums.add(next * 2);
            nums.add(next * 3);
            nums.add(next * 5);
        }
    }
}