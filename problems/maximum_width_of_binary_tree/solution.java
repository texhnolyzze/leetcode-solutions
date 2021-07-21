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
    public int widthOfBinaryTree(final TreeNode root) {
        int maxWidth = 1;
        root.val = 0;
        final Queue<TreeNode> queue = new LinkedList<>();
        final IdentityHashMap<TreeNode, TreeNode> parentOf = new IdentityHashMap<>();
        queue.add(root);
        TreeNode leftmost = null;
        TreeNode rightmost = null;
        int consideringLevel = 1;
        int currentLevelWidth = 0;
        while (!queue.isEmpty()) {
            final TreeNode node = queue.poll();
            final int currentLevel = node.val;
            if (currentLevel == consideringLevel + 1) {
                consideringLevel = currentLevel;
                maxWidth = Math.max(
                    maxWidth,
                    width(parentOf, leftmost, rightmost, currentLevel - 1, currentLevelWidth)
                );
                leftmost = null;
                rightmost = null;
                currentLevelWidth = 0;
            }
            if (node.left != null) {
                node.left.val = currentLevel + 1;
                queue.add(node.left);
                parentOf.put(node.left, node);
            }
            if (node.right != null) {
                node.right.val = currentLevel + 1;
                queue.add(node.right);
                parentOf.put(node.right, node);
            }
            if (currentLevel == consideringLevel) {
                currentLevelWidth++;
                if (leftmost == null) {
                    leftmost = node;
                }
                rightmost = node;
            }
        }

        if (leftmost != null) {
            maxWidth = Math.max(
                maxWidth,
                width(parentOf, leftmost, rightmost, consideringLevel, currentLevelWidth)
            );
        }
        return maxWidth;
    }

    private int width(
        final IdentityHashMap<TreeNode, TreeNode> parentOf,
        final TreeNode leftmost,
        final TreeNode rightmost,
        final int targetLevel,
        final int currentLevelWidth
    ) {
        if (leftmost == rightmost) {
            return 1;
        }
        int res = currentLevelWidth;
        TreeNode leftParent = parentOf.get(leftmost);
        TreeNode rightParent = parentOf.get(rightmost);
        TreeNode leftPrev = leftmost;
        TreeNode rightPrev = rightmost;
        while (leftParent != null) {
            if (leftParent != rightParent) {
                final int level = leftParent.val;
                if (leftParent.right == null) {
                    res += (1 << targetLevel - level - 1);
                } else if (leftParent.right != leftPrev) {
                    res += countNulls(leftParent.right, targetLevel);
                }
                if (rightParent.left == null) {
                    res += (1 << targetLevel - level - 1);
                } else if (rightParent.left != rightPrev) {
                    res += countNulls(rightParent.left, targetLevel);
                }
            } else {
                break;
            }
            leftPrev = leftParent;
            rightPrev = rightParent;
            leftParent = parentOf.get(leftParent);
            rightParent = parentOf.get(rightParent);
        }
        return res;
    }

    private int countNulls(
        final TreeNode node,
        final int targetLevel
    ) {
        final int level = node.val;
        if (level >= targetLevel) {
            return 0;
        }
        int res = 0;
        if (node.left == null) {
            res += (1 << targetLevel - level - 1);
        } else {
            res += countNulls(node.left, targetLevel);
        }
        if (node.right == null) {
            res += (1 << targetLevel - level - 1);
        } else {
            res += countNulls(node.right, targetLevel);
        }
        return res;
    }
}