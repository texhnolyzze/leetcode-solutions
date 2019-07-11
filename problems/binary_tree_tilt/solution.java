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
    private int tilt;
    
    public int findTilt(TreeNode root) {
        dfs(root);
        return tilt;
    }
    
    private int dfs(TreeNode root) {
        if (root == null)
            return 0;
        int leftSum = dfs(root.left);
        int rightSum = dfs(root.right);
        tilt += Math.abs(leftSum - rightSum);
        return root.val + leftSum + rightSum;
    }
}