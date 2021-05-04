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
    public List<TreeNode> generateTrees(int n) {
        return generate(1, n);
    }

    private List<TreeNode> generate(int from, int to) {
        int treeSize = to - from + 1;
        if (treeSize <= 0) {
            return Collections.emptyList();
        } else if (treeSize == 1) {
            return Collections.singletonList(new TreeNode(from));
        } else {
            List<TreeNode> res = new ArrayList<>();
            for (int i = from; i <= to; i++) {
                List<TreeNode> leftPossibleSubtrees = generate(from, i - 1);
                List<TreeNode> rightPossibleSubtrees = generate(i + 1, to);
                if (leftPossibleSubtrees.isEmpty()) {
                    for (TreeNode rightPossibleSubtree : rightPossibleSubtrees) {
                        TreeNode node = new TreeNode(i);
                        node.right = rightPossibleSubtree;
                        res.add(node);
                    }
                } else if (rightPossibleSubtrees.isEmpty()) {
                    for (TreeNode leftPossibleSubtree : leftPossibleSubtrees) {
                        TreeNode node = new TreeNode(i);
                        node.left = leftPossibleSubtree;
                        res.add(node);
                    }
                } else {
                    for (TreeNode leftPossibleSubtree : leftPossibleSubtrees) {
                        for (TreeNode rightPossibleSubtree : rightPossibleSubtrees) {
                            TreeNode node = new TreeNode(i);
                            node.left = leftPossibleSubtree;
                            node.right = rightPossibleSubtree;
                            res.add(node);
                        }
                    }
                }
            }
            return res;
        }
    }
}