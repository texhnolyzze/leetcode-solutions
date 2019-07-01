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
    public boolean isBalanced(TreeNode root) {
        int code = dfs(root);
        return code >= 0;
    }
    
    private static int dfs(TreeNode root) {
        if (root == null)
            return 0;
        int leftDepth = dfs(root.left);
        if (leftDepth == -1)
            return -1; 
        int rightDepth = dfs(root.right);
        if (rightDepth == -1)
            return -1;
        if (Math.abs(leftDepth - rightDepth) > 1) 
            return -1;
        return 1 + Math.max(leftDepth, rightDepth);
    }
}