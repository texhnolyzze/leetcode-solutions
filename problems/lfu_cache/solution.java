class LFUCache {

    private final Map<Integer, Integer> cache = new HashMap<>();
    private final Map<Integer, Integer> frequencyOf = new HashMap<>();
    private final Map<Integer, LinkedHashSet<Integer>> map = new HashMap<>();
    private int minFrequency;
    
    private final int capacity;
    public LFUCache(int capacity) {
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if (capacity == 0) return -1; // stupid test case
        Integer freq = frequencyOf.get(key);
        if (freq != null) {
            frequencyOf.put(key, freq + 1);
            map.compute(freq, (f, set) -> {
                set.remove(key);
                return set.isEmpty() ? null : set;
            });
            map.computeIfAbsent(freq + 1, f -> new LinkedHashSet<>()).add(key);
            if (!map.containsKey(minFrequency))
                minFrequency++;
            return cache.get(key);
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if (capacity == 0) return; // stupid test case
        if (cache.containsKey(key)) {
            cache.put(key, value);
            get(key);
            return;            
        }
        if (cache.size() == capacity) {
            map.compute(minFrequency, (f, set) -> {
                Iterator<Integer> it = set.iterator();
                Integer elem = it.next();
                it.remove();
                cache.remove(elem);
                frequencyOf.remove(elem);
                return it.hasNext() ? set : null;
            });
        }
        putWithZeroFrequency(key, value);
    }
    
    private void putWithZeroFrequency(int key, int value) {
        cache.put(key, value);
        frequencyOf.put(key, 0);
        map.computeIfAbsent(0, f -> new LinkedHashSet<>()).add(key);
        minFrequency = 0;
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */