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
    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null)
            return Collections.EMPTY_LIST;
        List<String> res = new ArrayList<>();
        if (root.left == null && root.right == null)
            res.add(Integer.toString(root.val));
        else {
            if (root.left != null) collectPaths(root.left, new StringBuilder().append(root.val).append("->"), res);
            if (root.right != null) collectPaths(root.right, new StringBuilder().append(root.val).append("->"), res);
        }
        return res;
    }

    private static void collectPaths(TreeNode root, StringBuilder sb, List<String> res) {
        String valAsString = Integer.toString(root.val);
        if (root.left == null && root.right == null) {
            sb.append(valAsString);
            res.add(sb.toString());
            sb.setLength(sb.length() - valAsString.length());
        } else {
            sb.append(valAsString).append("->");
            if (root.left != null) collectPaths(root.left, sb, res);
            if (root.right != null) collectPaths(root.right, sb, res);
            sb.setLength(sb.length() - (valAsString.length() + 2));
        }
    }
}