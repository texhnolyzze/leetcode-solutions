/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    public List<Integer> postorder(final Node root) {
        return postorder(root, new LinkedList<>());
    }

    private List<Integer> postorder(final Node root, final List<Integer> target) {
        if (root == null) {
            return target;
        }
        if (root.children != null) {
            for (final Node child : root.children) {
                postorder(child, target);
            }
        }
        target.add(root.val);
        return target;
    }
}