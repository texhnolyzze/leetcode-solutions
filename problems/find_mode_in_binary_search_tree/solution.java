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
    private final Map<Integer, Integer> freq = new HashMap<>();
    private int maxFreq;

    public int[] findMode(final TreeNode root) {
        maxFreq = 0;
        findMode0(root);
        return freq.entrySet().stream().filter(e -> e.getValue() == maxFreq).map(Map.Entry::getKey).mapToInt(Integer::intValue).toArray();
    }

    private void findMode0(final TreeNode root) {
        maxFreq = Math.max(
            maxFreq,
            freq.compute(
                root.val,
                (unused, count) -> count == null ? 1 : count + 1
            )
        );
        if (root.left != null) {
            findMode0(root.left);
        }
        if (root.right != null) {
            findMode0(root.right);
        }
    }
}