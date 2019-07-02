class MinStack {

    private static class Node {
        int val;
        int min;
        Node prev;
        Node(int val, int min) {this(val, min, null);}
        Node(int val, int min, Node prev) {
            this.val = val; 
            this.min = min; 
            this.prev = prev;
        }
    }
    
    private Node head;
    
    public MinStack() {}
    
    public void push(int x) {
        if (head == null) 
            head = new Node(x, x);
        else {
            if (x < head.min) 
                head = new Node(x, x, head);
            else
                head = new Node(x, head.min, head);
        }
    }
    
    public void pop() {
        head = head.prev;
    }
    
    public int top() {
        return head.val;
    }
    
    public int getMin() {
        return head.min;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */