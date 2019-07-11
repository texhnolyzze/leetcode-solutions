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
    private int max;
    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return max;
    }
    private int depth(TreeNode n) {
        if (n == null)
            return 0;
        int leftDepth = depth(n.left);
        int rightDepth = depth(n.right);
        max = Math.max(max, leftDepth + rightDepth);
        return 1 + Math.max(leftDepth, rightDepth);
    }
}