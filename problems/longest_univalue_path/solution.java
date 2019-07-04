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
    private int max = 0;
    
    public int longestUnivaluePath(TreeNode root) {
        if (root == null)
            return 0;
        longestUnivaluePath0(root);
        return max;
    }
    
    private void longestUnivaluePath0(TreeNode n) {
        if (n.left != null) {
            int leftDepth = dfsMaxDepthWithVal(n.left, n.val, 0);
            if (n.right != null) {
                max = Math.max(max, leftDepth + dfsMaxDepthWithVal(n.right, n.val, 0));
                longestUnivaluePath0(n.left);
                longestUnivaluePath0(n.right);
            } else {
                max = Math.max(max, leftDepth);
                longestUnivaluePath0(n.left);
            }
        } else {
            if (n.right != null) {
                int rightDepth = dfsMaxDepthWithVal(n.right, n.val, 0);
                max = Math.max(max, rightDepth);
                longestUnivaluePath0(n.right);
            }
        }
    }
    
    private int dfsMaxDepthWithVal(TreeNode n, int val, int depth) {
        if (n.val != val)
            return depth;
        depth++;
        int leftDepth = 0, rightDepth = 0;
        if (n.left != null)
            leftDepth = dfsMaxDepthWithVal(n.left, val, depth);
        if (n.right != null)
            rightDepth = dfsMaxDepthWithVal(n.right, val, depth);
        return Math.max(depth, Math.max(leftDepth, rightDepth));
    }
}