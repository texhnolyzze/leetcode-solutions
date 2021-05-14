import java.util.NavigableMap;

class TweetCounts {

    private static final Map<String, Integer> SECONDS = Map.of(
        "minute", 60,
        "hour", 3600,
        "day", 86400
    );

    private final Map<String, NavigableMap<Integer, Integer>> tweets;

    public TweetCounts() {
        tweets = new HashMap<>();
    }

    public void recordTweet(String tweetName, int time) {
        tweets.computeIfAbsent(
            tweetName,
            unused -> new TreeMap<>()
        ).compute(
            time,
            (unused, count) -> count == null ? 1 : count + 1
        );
    }

    public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
        final int seconds = SECONDS.get(freq);
        final int numChunks = (endTime - startTime) / seconds + 1;
        final List<Integer> chunks = new ArrayList<>(numChunks);
        for (int i = 0; i < numChunks; i++) {
            chunks.add(0);
        }
        NavigableMap<Integer, Integer> tweetTimeDistribution = tweets.get(tweetName);
        if (tweetTimeDistribution == null)
            return chunks;
        tweetTimeDistribution = tweetTimeDistribution.subMap(startTime, true, endTime, true);
        if (tweetTimeDistribution.isEmpty())
            return chunks;
        for (final Map.Entry<Integer, Integer> tweetTime : tweetTimeDistribution.entrySet()) {
            final int chunkIndex = (tweetTime.getKey() - startTime) / seconds;
            final int chunkValue = chunks.get(chunkIndex);
            chunks.set(chunkIndex, chunkValue + tweetTime.getValue());
        }
        return chunks;
    }

}

/**
 * Your TweetCounts object will be instantiated and called as such:
 * TweetCounts obj = new TweetCounts();
 * obj.recordTweet(tweetName,time);
 * List<Integer> param_2 = obj.getTweetCountsPerFrequency(freq,tweetName,startTime,endTime);
 */