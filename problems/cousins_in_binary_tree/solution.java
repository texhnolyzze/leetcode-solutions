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
    public boolean isCousins(TreeNode root, int x, int y) {
        Object[] xDepthAndParent = getDepthAndParentOf(x, null, root, 0);
        Object[] yDepthAndParent = getDepthAndParentOf(y, null, root, 0);
        int xDepth = (int) xDepthAndParent[0];
        int yDepth = (int) yDepthAndParent[0];
        return xDepth == yDepth && xDepthAndParent[1] != yDepthAndParent[1];
    }
    
    Object[] getDepthAndParentOf(int x, TreeNode parent, TreeNode n, int depth) {
        if (n == null)
            return null;
        if (n.val == x)
            return new Object[] {depth, parent};
        Object[] leftResult = getDepthAndParentOf(x, n, n.left, depth + 1);
        if (leftResult != null)
            return leftResult;
        return getDepthAndParentOf(x, n, n.right, depth + 1);
    }
}