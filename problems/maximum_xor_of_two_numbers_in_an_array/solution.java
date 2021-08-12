class Solution {
    public int findMaximumXOR(final int[] nums) {
        final BinaryTrie trie = new BinaryTrie();
        for (final int num : nums) {
            trie.insert(num);
        }
        int max = 0;
        for (final int num : nums) {
            max = Math.max(
                max,
                trie.maxXor(num)
            );
        }
        return max;
    }

    private static final class BinaryTrie {

        final Node root = new Node();

        public void insert(final int num) {
            Node curr = root;
            for (int i = 31; i >= 0; i--) {
                final boolean set = (num >>> i & 1) != 0;
                if (set) {
                    if (curr.right == null) {
                        curr.right = new Node();
                    }
                    curr = curr.right;
                } else {
                    if (curr.left == null) {
                        curr.left = new Node();
                    }
                    curr = curr.left;
                }
            }
        }

        public int maxXor(final int num) {
            Node curr = root;
            int xor = 0;
            for (int i = 31; i >= 0; i--) {
                final boolean set = (num >>> i & 1) != 0;
                if (set) {
                    if (curr.left == null) {
                        curr = curr.right;
                    } else {
                        curr = curr.left;
                        xor |= 1 << i;
                    }
                } else {
                    if (curr.right == null) {
                        curr = curr.left;
                    } else {
                        curr = curr.right;
                        xor |= 1 << i;
                    }
                }
                if (curr == null) {
                    return 0;
                }
            }
            return xor;
        }

        private static class Node {
            Node left;
            Node right;
        }

    }
}