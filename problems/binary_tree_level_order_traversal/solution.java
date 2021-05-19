/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(root, result, 1);
        return result;
    }

    private void dfs(
        final TreeNode root,
        final List<List<Integer>> result,
        final int depth
    ) {
        if (root == null)
            return;
        while (result.size() < depth) {
            result.add(new ArrayList<>());
        }
        result.get(depth - 1).add(root.val);
        dfs(root.left, result, depth + 1);
        dfs(root.right, result, depth + 1);
    }
}