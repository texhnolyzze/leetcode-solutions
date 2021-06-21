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
    public int maxSumBST(final TreeNode root) {
        final List<Result> results = maxSumBST0(root);
        return results.stream().filter(res -> res.maxSum >= 0).max(Comparator.comparingInt(res -> res.maxSum)).map(res -> res.maxSum).orElse(0);
    }

    private List<Result> maxSumBST0(
        final TreeNode root
    ) {
        if (root.left == null && root.right == null) {
            return Collections.singletonList(new Result(root.val, true, root.val, root.val));
        } else {
            final List<Result> res = new ArrayList<>(1);
            Result maxValid = null;
            Result maxInvalid = null;
            if (root.left != null) {
                final List<Result> lefts = maxSumBST0(root.left);
                if (root.right != null) {
                    final List<Result> rights = maxSumBST0(root.right);
                    for (final Result left : lefts) {
                        for (final Result right : rights) {
                            if (
                                left.valid &&
                                right.valid &&
                                root.left.val < root.val &&
                                root.val < root.right.val &&
                                left.max < root.val &&
                                root.val < right.min
                            ) {
                                if (root.val + left.maxSum + right.maxSum >= Math.max(left.maxSum, right.maxSum) && (maxValid == null || maxValid.maxSum < root.val + left.maxSum + right.maxSum)) {
                                    maxValid = new Result(root.val + left.maxSum + right.maxSum, true, left.min, right.max);
                                } else {
                                    if (maxValid == null || maxValid.maxSum < root.val + left.maxSum + right.maxSum) {
                                        maxValid = new Result(root.val + left.maxSum + right.maxSum, true, left.min, right.max);
                                    }
                                    if (maxInvalid == null || maxInvalid.maxSum < Math.max(left.maxSum, right.maxSum)) {
                                        maxInvalid = new Result(Math.max(left.maxSum, right.maxSum), false, -1, -1);
                                    }
                                }
                            } else {
                                if (maxInvalid == null || maxInvalid.maxSum < Math.max(left.maxSum, right.maxSum)) {
                                    maxInvalid = new Result(Math.max(left.maxSum, right.maxSum), false, -1, -1);
                                }
                            }
                        }
                    }
                } else {
                    for (final Result left : lefts) {
                        if (
                            left.valid &&
                            root.left.val < root.val &&
                            left.max < root.val
                        ) {
                            if (root.val + left.maxSum >= left.maxSum && (maxValid == null || maxValid.maxSum < root.val + left.maxSum)) {
                                maxValid = new Result(root.val + left.maxSum, true, left.min, root.val);
                            } else {
                                if (maxValid == null || maxValid.maxSum < root.val + left.maxSum) {
                                    maxValid = new Result(root.val + left.maxSum, true, left.min, root.val);
                                }
                                if (maxInvalid == null || maxInvalid.maxSum < left.maxSum) {
                                    maxInvalid = new Result(left.maxSum, false, -1, -1);
                                }
                            }
                        } else {
                            if (maxInvalid == null || maxInvalid.maxSum < left.maxSum) {
                                maxInvalid = new Result(left.maxSum, false, -1, -1);
                            }
                        }
                    }
                }
            } else {
                final List<Result> rights = maxSumBST0(root.right);
                for (final Result right : rights) {
                    if (
                        right.valid &&
                        root.val < root.right.val &&
                        root.val < right.min
                    ) {
                        if (root.val + right.maxSum >= right.maxSum && (maxValid == null || maxValid.maxSum < root.val + right.maxSum)) {
                            maxValid = new Result(root.val + right.maxSum, true, root.val, right.max);
                        } else {
                            if (maxValid == null || maxValid.maxSum < root.val + right.maxSum) {
                                maxValid = new Result(root.val + right.maxSum, true, root.val, right.max);
                            }
                            if (maxInvalid == null || maxInvalid.maxSum < right.maxSum) {
                                maxInvalid = new Result(right.maxSum, false, -1, -1);
                            }
                        }
                    } else {
                        if (maxInvalid == null || maxInvalid.maxSum < right.maxSum) {
                            maxInvalid = new Result(right.maxSum, false, -1, -1);
                        }
                    }
                }
            }
            if (maxValid != null) {
                res.add(maxValid);
            }
            if (maxInvalid != null) {
                res.add(maxInvalid);
            }
            return res;
        }
    }

    private static class Result {
        final int min;
        final int max;
        final boolean valid;
        final int maxSum;
        Result(final int maxSum, final boolean valid, final int min, final int max) {
            this.min = min;
            this.max = max;
            this.valid = valid;
            this.maxSum = maxSum;
        }
    }
}