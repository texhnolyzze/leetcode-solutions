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
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null)
            return false;
        return hasPathSum0(root, sum, 0);
    }
    
    private static boolean hasPathSum0(TreeNode n, int sum, int currSum) {
        if (n.left == null) {
            if (n.right == null)
                return sum == currSum + n.val;
            return hasPathSum0(n.right, sum, currSum + n.val);
        } else {
            if (n.right == null) 
                return hasPathSum0(n.left, sum, currSum + n.val);
            return hasPathSum0(n.left, sum, currSum + n.val) || hasPathSum0(n.right, sum, currSum + n.val);
        }
    }
}