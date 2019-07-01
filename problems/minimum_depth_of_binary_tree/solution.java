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
    public int minDepth(TreeNode root) {
        if (root == null)
            return 0;
        return minDepth0(root, 1);
    }
    
    private static int minDepth0(TreeNode n, int depth) {
        if (n.left == null) {
            if (n.right == null)
                return depth;
            return minDepth0(n.right, depth + 1);
        } else {
            if (n.right == null) 
                return minDepth0(n.left, depth + 1);
            return Math.min(minDepth0(n.left, depth + 1), minDepth0(n.right, depth + 1));
        }
    }
    
}