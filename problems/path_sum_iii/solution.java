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
    private int count;
    public int pathSum(TreeNode root, int sum) {
        pathSum(root, sum, 0, new HashMap<>());
        return count;
    }
    
    private void pathSum(TreeNode root, int sum, int prefixSum, Map<Integer, Integer> map) {
        if (root == null)
            return;
        prefixSum += root.val;
        int numPrev = map.getOrDefault(prefixSum - sum, 0);
        count += numPrev;
        if (prefixSum == sum) {
            count++;
        }
        map.merge(prefixSum, 1, Integer::sum);
        pathSum(root.left, sum, prefixSum, map);
        pathSum(root.right, sum, prefixSum, map);
        map.merge(prefixSum, -1, Integer::sum);
    }
}