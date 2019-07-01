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
    public TreeNode sortedArrayToBST(int[] nums) {
        return toBST(nums, 0, nums.length - 1);
    }
    
    private TreeNode toBST(int[] nums, int leftIdx, int rightIdx) {
        if (leftIdx > rightIdx)
            return null;
        if (leftIdx == rightIdx)
            return new TreeNode(nums[leftIdx]);
        int mid = leftIdx + (rightIdx - leftIdx) / 2;
        TreeNode n = new TreeNode(nums[mid]);
        n.left = toBST(nums, leftIdx, mid - 1);
        n.right = toBST(nums, mid + 1, rightIdx);
        return n;
    }
}