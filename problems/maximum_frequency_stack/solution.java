class FreqStack {

    private final TreeSet<Entry> elements;
    private final Map<Integer, Entry> index;
    private int time;

    public FreqStack() {
        this.elements = new TreeSet<>(
            (elem1, elem2) -> {
                final int fCmp = Integer.compare(elem1.freq, elem2.freq);
                if (fCmp == 0) {
                    return Integer.compare(elem1.times.get(0), elem2.times.get(0));
                }
                return fCmp;
            }
        );
        this.index = new HashMap<>();
    }

    public void push(final int val) {
        final Entry entry = index.computeIfAbsent(
            val,
            unused -> new Entry(val)
        );
        elements.remove(entry);
        entry.freq++;
        entry.times.add(0, time++);
        elements.add(entry);
    }

    public int pop() {
        final Entry last = elements.pollLast();
        last.freq--;
        last.times.remove(0);
        if (last.freq != 0) {
            elements.add(last);
        }
        return last.elem;
    }

    private static class Entry {
        int freq;
        final int elem;
        final List<Integer> times;
        private Entry(
            final int elem
        ) {
            this.elem = elem;
            this.times = new LinkedList<>();
        }
    }

}

/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack obj = new FreqStack();
 * obj.push(val);
 * int param_2 = obj.pop();
 */