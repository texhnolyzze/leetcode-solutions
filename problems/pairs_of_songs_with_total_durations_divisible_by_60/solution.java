class Solution {
    public static int numPairsDivisibleBy60(int[] time) {
        Map<Integer, Integer> m = new HashMap<>();
        int num = 0;
        for (int i = 0; i < time.length; i++) {
            int numPrev = m.getOrDefault(time[i] % 60 == 0 ? 0 : 60 - (time[i] % 60), 0);
            num += numPrev;
            m.merge(time[i] % 60, 1, Integer::sum);
        }
        return num;
    }
}