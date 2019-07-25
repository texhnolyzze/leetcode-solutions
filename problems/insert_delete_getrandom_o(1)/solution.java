import java.util.concurrent.ThreadLocalRandom;


class RandomizedSet {

        private int size;
        private int[] elems = new int[8];
        private final Map<Integer, Integer> indexOf = new HashMap<>();
        public RandomizedSet() {}

        public boolean insert(int val) {
            if (indexOf.containsKey(val))
                return false;
            if (size == elems.length) 
                resize(2 * size);
            indexOf.put(val, size);
            elems[size++] = val;
            return true;
        }

        public boolean remove(int val) {
            if (!indexOf.containsKey(val))
                return false;
            int i = indexOf.get(val);
            elems[i] = elems[--size];
            indexOf.put(elems[i], i);
            indexOf.remove(val);
            return true;
        }
        
        private void resize(int newSize) {
            int[] elems = new int[newSize];
            System.arraycopy(this.elems, 0, elems, 0, size);
            this.elems = elems;
        }

        public int getRandom() {
            ThreadLocalRandom rand = ThreadLocalRandom.current();
            return elems[rand.nextInt(size)];
        }
        
    }
/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */