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
    public int rangeSumBST(
        final TreeNode root,
        final int low,
        final int high
    ) {
        if (root == null) {
            return 0;
        }
        final int left = rangeSumBST(root.left, low, high);
        final int right = rangeSumBST(root.right, low, high);
        return low <= root.val && root.val <= high ? root.val + left + right : left + right;
    }
}