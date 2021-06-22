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
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            flatten(root.left);
            if (root.right != null) {
                TreeNode leftJoinPoint = root.left;
                while (leftJoinPoint.right != null) {
                    leftJoinPoint = leftJoinPoint.right;
                }
                TreeNode temp = root.right;
                root.right = root.left;
                root.left = null;
                flatten(temp);
                leftJoinPoint.right = temp;
            } else {
                root.right = root.left;
                root.left = null;
            }
        } else {
            flatten(root.right);
        }
    }
}