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
    public TreeNode pruneTree(TreeNode root) {
        if (root == null)
            return null;
        dfs(root);
        return root;
    }

    private boolean dfs(TreeNode root) {
        boolean leftHasOne = root.left == null ? false : dfs(root.left);
        if (!leftHasOne)
            root.left = null;
        boolean rightHasOne = root.right == null ? false : dfs(root.right);
        if (!rightHasOne)
            root.right = null;
        return root.val == 1 || leftHasOne || rightHasOne;
    }
}