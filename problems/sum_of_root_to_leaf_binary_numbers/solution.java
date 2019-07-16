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
    private int sum;
    public int sumRootToLeaf(TreeNode root) {
        dfs(root, 0, 0);
        return sum;
    }
    
    private void dfs(TreeNode root, int n, int depth) {
        n = setBit(depth, root.val, n);
        if (root.left == null && root.right == null)
            sum += reverseBits(n, depth + 1);
        else {
            if (root.left != null)
                dfs(root.left, n, depth + 1);
            if (root.right != null)
                dfs(root.right, n, depth + 1);
        }
    }
    
    private static int setBit(int idx, int bit, int n) {
        return (n & ~(1 << idx)) | (bit << idx);
    }
    
    private static int reverseBits(int n, int numBits) {
        return Integer.reverse(n) >>> (32 - numBits);
    }
}