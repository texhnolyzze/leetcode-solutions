import java.util.LinkedList;
import java.util.Queue;
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
    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        Queue<TreeNode> leftQueue = new LinkedList<>();
        Queue<TreeNode> rightQueue = new LinkedList<>();
        leftQueue.add(root.left);
        rightQueue.add(root.right);
        while (!leftQueue.isEmpty() && !rightQueue.isEmpty()) {
            TreeNode leftNode = leftQueue.poll();
            TreeNode rightNode = rightQueue.poll();
            if (leftNode == null) {
                if (rightNode != null)
                    return false;
                else
                    continue;
            } else {
                if (rightNode == null)
                    return false;
            }
            if (leftNode.val != rightNode.val)
                return false;
            leftQueue.add(leftNode.left);
            leftQueue.add(leftNode.right);
            rightQueue.add(rightNode.right);
            rightQueue.add(rightNode.left);
        }
        return true;
    }
}