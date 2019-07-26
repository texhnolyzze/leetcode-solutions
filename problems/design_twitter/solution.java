public class Twitter {
        
        private final int MAX_RECENT_TWEETS = 10;
        
        private final Map<Integer, Integer> timeOf = new HashMap<>();
        private final Map<Integer, List<Integer>> tweetsOf = new HashMap<>();
        private final Map<Integer, Set<Integer>> followersOf = new HashMap<>();
        private final Map<Integer, Set<Integer>> followeesOf = new HashMap<>();
        private final Map<Integer, LinkedList<Integer>> recentTweetsOf = new HashMap<>();
        
        private int time;
        
        public Twitter() {}

//      O(userFollowers.get(usedId).size())
        public void postTweet(int userId, int tweetId) {
            timeOf.put(tweetId, time++);
            tweetsOf.computeIfAbsent(userId, id -> new LinkedList<>()).add(tweetId);
            Set<Integer> followers = followersOf.getOrDefault(userId, Collections.EMPTY_SET);
            addRecentTweet(tweetId, userId);
            for (int followerId : followers) 
                addRecentTweet(tweetId, followerId);
        }
        
        private void addRecentTweet(int tweetId, int userId) {
            LinkedList<Integer> recentTweets = recentTweetsOf.computeIfAbsent(userId, id -> new LinkedList<>());
            recentTweets.addFirst(tweetId);
            if (recentTweets.size() > MAX_RECENT_TWEETS)
                recentTweets.removeLast();
        }
        
//      O(1)
        public List<Integer> getNewsFeed(int userId) {
            List<Integer> recentTweetsList = recentTweetsOf.get(userId);
            if (recentTweetsList == null)
                return Collections.EMPTY_LIST;
            return recentTweetsList;
        }

//      O(MAX_RECENT_TWEETS * log(MAX_RECENT_TWEETS))
        public void follow(int followerId, int followeeId) {
            if (followerId == followeeId)
                return;
            Set<Integer> followers = followersOf.computeIfAbsent(followeeId, id -> new HashSet<>());
            if (followers.contains(followerId))
                return;
            followers.add(followerId);
            followeesOf.computeIfAbsent(followerId, id -> new HashSet<>()).add(followeeId);
            recentTweetsOf.compute(followerId, (id, recentTweetsList) -> {
                List<Integer> followeeTweets = tweetsOf.get(followeeId);
                if (followeeTweets == null)
                    return recentTweetsList;
                if (recentTweetsList == null)
                    recentTweetsList = new LinkedList<>();
                ListIterator<Integer> tweetsIdIterator = followeeTweets.listIterator(followeeTweets.size());
                for (int i = 0; i < MAX_RECENT_TWEETS && tweetsIdIterator.hasPrevious(); i++)
                    recentTweetsList.add(tweetsIdIterator.previous());
                sortTweetsByTime(recentTweetsList);
                remainMaxRecentTweets(recentTweetsList);
                return recentTweetsList;
            });
        }

        public void unfollow(int followerId, int followeeId) {
            if (followerId == followeeId)
                return;
            Set<Integer> followees = followeesOf.get(followerId);
            if (followees == null || !followees.contains(followeeId))
                return;
            followees.remove(followeeId);
            followersOf.get(followeeId).remove(followerId);
            List<Integer> followeeTweets = tweetsOf.get(followeeId);
            if (followeeTweets == null)
                return;
            recentTweetsOf.compute(followerId, (id, recentTweetsList) -> {
                recentTweetsList.removeIf(followeeTweets::contains);
                List<List<Integer>> tweets = new LinkedList<>();
                tweets.add(tweetsOf.getOrDefault(followerId, Collections.EMPTY_LIST));
                for (Integer followee : followees)
                    tweets.add(tweetsOf.getOrDefault(followee, Collections.EMPTY_LIST));
                addRecentTweets(recentTweetsList, tweets);
                sortTweetsByTime(recentTweetsList);
                return recentTweetsList;
            });
        }
        
        private void addRecentTweets(LinkedList<Integer> recentTweetsList, List<List<Integer>> tweets) {
            ListIterator<Integer>[] iterators = new ListIterator[tweets.size()];
            int i = 0;
            for (List<Integer> t : tweets)
                iterators[i++] = t.listIterator(t.size());
            int iteratorIndex = -1;
            int mostRecentTweetId = -1;
            int mostRecentTweetTime = Integer.MIN_VALUE;
            int k = MAX_RECENT_TWEETS - recentTweetsList.size();
            for (i = 0; i < k; i++) {
                for (int j = 0; j < iterators.length; j++) {
                    ListIterator<Integer> it = iterators[j];
                    boolean callNext = false;
                    while (it.hasPrevious()) {
                        int tweetId = it.previous();
                        if (!recentTweetsList.contains(tweetId)) {
                            if (timeOf.get(tweetId) > mostRecentTweetTime) {
                                mostRecentTweetId = tweetId;
                                mostRecentTweetTime = timeOf.get(tweetId);
                                iteratorIndex = j;
                            }
                            callNext = true;
                            break;
                        }
                    }
                    if (callNext) // no peek() method in iterator, sooo...
                        it.next();
                }
                if (mostRecentTweetId != -1) {
                    recentTweetsList.add(mostRecentTweetId);
                    mostRecentTweetId = -1;
                    mostRecentTweetTime = Integer.MIN_VALUE;
                    iterators[iteratorIndex].previous();
                    iteratorIndex = -1;
                } else 
                    return;
            }
        }
        
        private void sortTweetsByTime(List<Integer> recentTweets) {
            Collections.sort(recentTweets, (tweetId1, tweetId2) -> {
                return -Integer.compare(timeOf.get(tweetId1), timeOf.get(tweetId2));
            });
        }
        
        private void remainMaxRecentTweets(List<Integer> recentTweets) {
            ListIterator<Integer> recentTweetsIterator = recentTweets.listIterator(Math.min(MAX_RECENT_TWEETS, recentTweets.size()));
            while (recentTweetsIterator.hasNext()) {
                recentTweetsIterator.next();
                recentTweetsIterator.remove();
            }
        }
        
    }

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */