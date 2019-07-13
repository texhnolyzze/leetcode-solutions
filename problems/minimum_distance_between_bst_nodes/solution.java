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

    private int min = Integer.MAX_VALUE;
    private InorderTreeVisitor visitor = new InorderTreeVisitor();
    public int minDiffInBST(TreeNode root) {
        traverse(root);
        return min;
    }

    private void traverse(TreeNode root) {
        if (root.left != null)
            traverse(root.left);
        visitor.visit(root.val);
        if (root.right != null)
            traverse(root.right);
    }
    
    private class InorderTreeVisitor {
        private int prevValue = -1000000000;
        private void visit(int value) {
            min = Math.min(min, value - prevValue);
            prevValue = value;
        }
    }
}