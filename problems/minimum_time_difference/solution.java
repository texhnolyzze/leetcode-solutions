class Solution {
    public int findMinDifference(List<String> timePoints) {
        int n = timePoints.size();
        ToIntFunction<String> mapper = time -> {
            int res = 0;
            res += (time.charAt(0) - '0') * 10 * 60;
            res += (time.charAt(1) - '0') * 60;
            res += (time.charAt(3) - '0') * 10;
            res += (time.charAt(4) - '0');
            return res;
        };
        int[] minutes = timePoints.stream().mapToInt(mapper).sorted().toArray();
        int minDiff = Math.min(minutes[n - 1] - minutes[0], 1440 - (minutes[n - 1] - minutes[0]));
        for (int i = 0; i < n - 1; i++) {
            int diff = Math.min(minutes[i + 1] - minutes[i], 1440 - (minutes[i + 1] - minutes[0]));
            minDiff = Math.min(minDiff, diff);
        }
        return minDiff;
    }
}