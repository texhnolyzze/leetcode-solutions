/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {}

    public Node(int _val,List<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
};
*/
class Solution {
    private Map<Node, Node> cloneOf = new HashMap<>();
    public Node cloneGraph(Node node) {
        return deepClone(node);
    }

    private Node deepClone(Node node) {
        Node clone = cloneOf.get(node);
        if (clone != null)
            return clone;
        clone = new Node();
        clone.val = node.val;
        clone.neighbors = new LinkedList<>();
        cloneOf.put(node, clone);
        for (Node n : node.neighbors) {
            clone.neighbors.add(deepClone(n));
        }
        return clone;
    }
}