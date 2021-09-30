class Solution {
    private static final int MOD = (int) (1E9 + 7);

    public int numDecodings(final String s) {
        final Trie trie = new Trie();
        for (int i = 0; i < 26; i++) {
            trie.add(Integer.toString(i + 1), 1);
        }
        trie.add("*", 9);
        trie.add("**", 15);
        trie.add("1*", 9);
        trie.add("2*", 6);
        trie.add("*0", 2);
        trie.add("*1", 2);
        trie.add("*2", 2);
        trie.add("*3", 2);
        trie.add("*4", 2);
        trie.add("*5", 2);
        trie.add("*6", 2);
        trie.add("*7", 1);
        trie.add("*8", 1);
        trie.add("*9", 1);
        final int[] memtable = new int[s.length()];
        Arrays.fill(memtable, -1);
        return numDecodings(s, 0, trie, memtable);
    }

    private int numDecodings(
        final String s,
        final int from,
        final Trie trie,
        final int[] memtable
    ) {
        if (from >= s.length()) {
            return 0;
        }
        long res = memtable[from];
        if (res != -1) {
            return (int) res;
        }
        if (s.length() - from >= 2) {
            final char c1 = s.charAt(from);
            final char c2 = s.charAt(from + 1);
            final long c1n = trie.numDecodings(c1);
            if (c1n != 0) {
                res = (c1n * numDecodings(s, from + 1, trie, memtable)) % MOD;
                final long c1c2n = trie.numDecodings(c1, c2);
                if (c1c2n != 0) {
                    if (from + 2 < s.length()) {
                        res = (res + c1c2n * numDecodings(s, from + 2, trie, memtable)) % MOD;
                    } else {
                        res = (res + c1c2n) % MOD;
                    }
                }
            } else {
                res = 0;
            }
        } else {
            res = trie.numDecodings(s.charAt(from));
        }
        memtable[from] = (int) res;
        return (int) res;
    }

    private static class Trie {

        final TrieNode root = new TrieNode(0);

        void add(final String s, final int numDecodings) {
            TrieNode curr = root;
            for (int i = 0; i < s.length(); i++) {
                final char c = s.charAt(i);
                final int idx = c == '*' ? 0 : c - '0' + 1;
                if (i == s.length() - 1) {
                    curr.child[idx] = new TrieNode(numDecodings);
                } else {
                    curr = curr.child[idx];
                }
            }
        }

        long numDecodings(final char c) {
            final int idx = c == '*' ? 0 : c - '0' + 1;
            final TrieNode node = root.child[idx];
            if (node == null) {
                return 0;
            }
            return node.numDecodings;
        }

        long numDecodings(
            final char c1,
            final char c2
        ) {
            final int idx1 = c1 == '*' ? 0 : c1 - '0' + 1;
            final int idx2 = c2 == '*' ? 0 : c2 - '0' + 1;
            final TrieNode node1 = root.child[idx1];
            if (node1 == null) {
                return 0;
            }
            final TrieNode node2 = node1.child[idx2];
            if (node2 == null) {
                return 0;
            }
            return node2.numDecodings;
        }

    }

    private static class TrieNode {

        final TrieNode[] child = new TrieNode[27];
        final int numDecodings;

        private TrieNode(final int numDecodings) {
            this.numDecodings = numDecodings;
        }

    }
}