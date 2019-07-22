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
    private int val;
    private int maxDepth = Integer.MIN_VALUE;
    private int maxLeftTransitionCount = Integer.MIN_VALUE;
    public int findBottomLeftValue(TreeNode root) {
                dfs(root, 0, 0);

        return val;
    }

    private void dfs(TreeNode root, int depth, int leftTransitionCount) {
        if ((depth > maxDepth) || (depth == maxDepth && leftTransitionCount > maxLeftTransitionCount)) {
            this.val = root.val;
            this.maxDepth = depth;
            this.maxLeftTransitionCount = leftTransitionCount;
        }
        if (root.left != null)
            dfs(root.left, depth + 1, leftTransitionCount + 1);
        if (root.right != null)
            dfs(root.right, depth + 1, leftTransitionCount);
    }
}