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
    public boolean findTarget(TreeNode root, int k) {
        return traverse(root, k, new HashSet<>());
    }

    private boolean traverse(TreeNode root, int k, Set<Integer> set) {
        if (root.left != null) {
            boolean b = traverse(root.left, k, set);
            if (b)
                return true;
        }
        if (set.contains(k - root.val))
            return true;
        set.add(root.val);
        if (root.right != null)
            return traverse(root.right, k, set);
        return false;
    }
}