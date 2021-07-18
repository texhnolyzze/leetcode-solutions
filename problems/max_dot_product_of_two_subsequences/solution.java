class Solution {
    public int maxDotProduct(
        final int[] nums1,
        final int[] nums2
    ) {
        final Integer[][] max = new Integer[nums1.length][nums2.length];
        return maxDotProduct(nums1, nums2, 0, 0, max);
    }

    private int maxDotProduct(
        final int[] nums1,
        final int[] nums2,
        final int from1,
        final int from2,
        final Integer[][] max
    ) {
        Integer res = max[from1][from2];
        if (res != null) {
            return res;
        }
        final int product = nums1[from1] * nums2[from2];
        if (from1 == nums1.length - 1) {
            if (from2 == nums2.length - 1) {
                res = product;
            } else {
                res = Math.max(
                    product,
                    maxDotProduct(nums1, nums2, from1, from2 + 1, max)
                );
            }
        } else {
            if (from2 == nums2.length - 1) {
                res = Math.max(
                    product,
                    maxDotProduct(nums1, nums2, from1 + 1, from2, max)
                );
            } else {
                final int sub = maxDotProduct(nums1, nums2, from1 + 1, from2 + 1, max);
                res = Math.max(
                    product + sub,
                    Math.max(
                        product,
                        Math.max(
                            sub,
                            Math.max(
                                maxDotProduct(nums1, nums2, from1 + 1, from2, max),
                                maxDotProduct(nums1, nums2, from1, from2 + 1, max)
                            )
                        )
                    )
                );
            }
        }
        max[from1][from2] = res;
        return res;
    }
}