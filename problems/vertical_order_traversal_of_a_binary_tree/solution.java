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
    public List<List<Integer>> verticalTraversal(final TreeNode root) {
        final TreeMap<Integer, TreeMap<Integer, List<Integer>>> sorted = new TreeMap<>();
        verticalTraversal(root, 0, 0, sorted);
        final List<List<Integer>> res = new ArrayList<>(sorted.size());
        for (final Map.Entry<Integer, TreeMap<Integer, List<Integer>>> col : sorted.entrySet()) {
            final TreeMap<Integer, List<Integer>> rows = col.getValue();
            final ArrayList<Integer> all = new ArrayList<>(rows.size());
            for (final Map.Entry<Integer, List<Integer>> row : rows.entrySet()) {
                all.addAll(row.getValue());
            }
            res.add(all);
        }
        return res;
    }

    private void verticalTraversal(
        final TreeNode root,
        final int row,
        final int col,
        final TreeMap<Integer, TreeMap<Integer, List<Integer>>> target
    ) {
        if (root == null) {
            return;
        }
        verticalTraversal(root.left, row + 1, col - 1, target);
        final List<Integer> list = target.computeIfAbsent(
            col,
            unused -> new TreeMap<>()
        ).computeIfAbsent(
            row,
            unused -> new ArrayList<>()
        );
        final int idx = Collections.binarySearch(list, root.val);
        if (idx >= 0) {
            list.add(idx, root.val);
        } else {
            list.add(-idx - 1, root.val);
        }
        verticalTraversal(root.right, row + 1, col + 1, target);
    }
}