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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p == q)
            return p;
        return p.val < q.val ? lcs(root, p.val, q.val) : lcs(root, q.val, p.val);
    }
    
    private TreeNode lcs(TreeNode root, int p, int q) {
        if (p < root.val) {
            if (q >= root.val)
                return root;
            else 
                return lcs(root.left, p, q);
        } else {
            if (p > root.val)
                return lcs(root.right, p, q);
            return root;
        }
    }
}