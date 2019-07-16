class RecentCounter {

    public RecentCounter() {
        
    }
    
        Queue<Integer> q = new LinkedList<>();
        public int ping(int t) {
            if (q.isEmpty()) {
                q.add(t);
                return 1;
            } 
            while (!q.isEmpty() && q.peek() < t - 3000)
                q.poll();
            q.add(t);
            return q.size();
        }
}

/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter obj = new RecentCounter();
 * int param_1 = obj.ping(t);
 */