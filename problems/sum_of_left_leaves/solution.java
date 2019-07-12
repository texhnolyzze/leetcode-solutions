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
    private int leftLeavesSum;
    public int sumOfLeftLeaves(TreeNode root) {
        if (root != null)
            dfs(root);
        return leftLeavesSum;
    }

    private void dfs(TreeNode root) {
        if (root.left != null) {
            if (root.left.left == null && root.left.right == null)
                leftLeavesSum += root.left.val;
            else
                dfs(root.left);
        }
        if (root.right != null)
            dfs(root.right);
    }
}