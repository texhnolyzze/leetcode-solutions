class MyQueue {
        
        private final LinkedList<Integer> pushStack = new LinkedList<>();
        private final LinkedList<Integer> popStack = new LinkedList<>();

        public MyQueue() {}

        public void push(int x) {
            pushStack.addLast(x);
        }

        public int pop() {
            if (!popStack.isEmpty())
                return popStack.pollLast();
            ListIterator<Integer> it = pushStack.listIterator(pushStack.size());
            while (it.hasPrevious()) {
                popStack.add(it.previous());
                it.remove();
            }
            return popStack.pollLast();
        }

        public int peek() {
            if (!popStack.isEmpty())
                return popStack.peekLast();
            ListIterator<Integer> it = pushStack.listIterator(pushStack.size());
            while (it.hasPrevious()) {
                popStack.add(it.previous());
                it.remove();
            }
            return popStack.peekLast();
        }

        public boolean empty() {
            return popStack.isEmpty() && pushStack.isEmpty();
        }
        
    }

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */