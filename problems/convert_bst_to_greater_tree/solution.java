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
    ReverseInorderTraversalVisitor visitor = new ReverseInorderTraversalVisitor();
    public TreeNode convertBST(TreeNode root) {
        reverseInorderTraversal(root);
        return root;
    }

    private void reverseInorderTraversal(TreeNode n) {
        if (n == null)
            return;
        reverseInorderTraversal(n.right);
        visitor.visit(n);
        reverseInorderTraversal(n.left);        
    }
    
    private static class ReverseInorderTraversalVisitor {
        private int sumAggregate;
        private void visit(TreeNode n) {
            int nVal = n.val;
            n.val += sumAggregate;
            sumAggregate += nVal;
        }
    }
}