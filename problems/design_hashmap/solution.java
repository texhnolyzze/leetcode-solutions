class MyHashMap {

    private final int[] table;

    public MyHashMap() {
        this.table = new int[(int) (1e6 + 1)];
    }

    public void put(int key, int value) {
        table[key] = value + 1;
    }

    public int get(int key) {
        return table[key] - 1;
    }

    public void remove(int key) {
        table[key] = 0;
    }

}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */