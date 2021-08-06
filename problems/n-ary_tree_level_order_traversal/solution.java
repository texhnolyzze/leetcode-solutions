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
    public List<List<Integer>> levelOrder(final Node root) {
        if (root == null) {
            return Collections.emptyList();
        }
        final List<List<Integer>> res = new ArrayList<>();
        final Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            final Node node = queue.poll();
            final int level = node.val >>> 16;
            if (res.size() < level + 1) {
                res.add(new ArrayList<>());
            }
            res.get(level).add(node.val & 0xffff);
            final List<Node> children = node.children;
            if (children == null) {
                continue;
            }
            final int size = children.size();
            for (int i = 0; i < size; i++) {
                final Node child = children.get(i);
                child.val = child.val | level + 1 << 16;
                queue.add(child);
            }
        }
        return res;
    }
}