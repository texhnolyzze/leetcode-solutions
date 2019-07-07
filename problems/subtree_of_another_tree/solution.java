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
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null)
            return false;
        boolean b = check(s, t);
        if (b)
            return true;
        return isSubtree(s.left, t) || isSubtree(s.right, t);
    }
    
    private boolean check(TreeNode n1, TreeNode n2) {
        if (n1 == null) {
            return n2 == null;
        } else {
            if (n2 == null)
                return false;
            if (n1.val != n2.val)
                return false;
            return check(n1.left, n2.left) && check(n1.right, n2.right);
        }
    }
}