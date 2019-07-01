/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
//      linked list should be enough, cause tree depth is small 
//      get(index, List) and add(index, List) will run fast
        List<List<Integer>> result = new ArrayList<>(); 
        if (root != null)
            fill(root, 0, result);
        for (int i = 0, j = result.size() - 1; i < j; i++, j--) 
            Collections.swap(result, i, j);
        return result;
    }
    
    private void fill(TreeNode n, int depth, List<List<Integer>> sink) {
        if (sink.size() < depth + 1) {
            sink.add(new LinkedList<>());
        }
        List<Integer> depthNodeValues = sink.get(depth);
        depthNodeValues.add(n.val);
        if (n.left != null) 
            fill(n.left, depth + 1, sink);
        if (n.right != null)
            fill(n.right, depth + 1, sink);
    }
}