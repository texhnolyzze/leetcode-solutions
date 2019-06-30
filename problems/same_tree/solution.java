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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null) 
            return q == null;
        else
            if (q == null)
                return false;
            else
                return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}