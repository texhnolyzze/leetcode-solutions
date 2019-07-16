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
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> ans = new ArrayList<>();
        List<Integer> numElementsOnSameDepth = new ArrayList<>();
        dfs(root, 0, ans, numElementsOnSameDepth);
        for (int i = 0; i < ans.size(); i++) {
            ans.set(i, ans.get(i) / numElementsOnSameDepth.get(i));
        }
        return ans;
    }

    private void dfs(TreeNode root, int depth, List<Double> ans, List<Integer> numElementsOnSameDepth) {
        if (ans.size() <= depth) {
            ans.add(new Double(root.val));
            numElementsOnSameDepth.add(1);
        } else {
            ans.set(depth, ans.get(depth) + root.val);
            numElementsOnSameDepth.set(depth, numElementsOnSameDepth.get(depth) + 1);            
        }
        if (root.left != null)
            dfs(root.left, depth + 1, ans, numElementsOnSameDepth);
        if (root.right != null)
            dfs(root.right, depth + 1, ans, numElementsOnSameDepth);
    }
}