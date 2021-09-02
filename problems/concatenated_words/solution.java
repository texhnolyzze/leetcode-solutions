class Solution {
    public List<String> findAllConcatenatedWordsInADict(final String[] words) {
        final TrieNode root = new TrieNode();
        for (final String word : words) {
            add(root, word);
        }
        final int[][] memtable = new int[words.length][];
        for (int i = 0; i < words.length; i++) {
            final String word = words[i];
            memtable[i] = new int[word.length()];
        }
        final List<String> res = new LinkedList<>();
        for (int i = 0; i < words.length; i++) {
            final String word = words[i];
            if (
                concatenated(
                    word,
                    root,
                    0,
                    memtable[i]
                )
            ) {
                res.add(word);
            }
        }
        return res;
    }

    private boolean concatenated(
        final String word,
        final TrieNode root,
        final int from,
        final int[] memtable
    ) {
        if (from >= word.length()) {
            return false;
        }
        int res = memtable[from];
        if (res != 0) {
            return res == 1;
        }
        res = -1;
        TrieNode curr = root;
        for (int i = from; i < word.length(); i++) {
            final char c = word.charAt(i);
            final int idx = c - 'a';
            final TrieNode next = curr.child[idx];
            if (next == null) {
                break;
            }
            if (
                next.keyword != null &&
                next.keyword != word &&
                (
                    concatenated(
                        word,
                        root,
                        i + 1,
                        memtable
                    ) ||
                    i == word.length() - 1
                )
            ) {
                res = 1;
                break;
            }
            curr = next;
        }
        memtable[from] = res;
        return res == 1;
    }

    private static class TrieNode {

        String keyword;
        final TrieNode[] child;

        private TrieNode() {
            this.child = new TrieNode[26];
        }

    }

    private void add(final TrieNode root, final String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            final char c = word.charAt(i);
            final int idx = c - 'a';
            final TrieNode temp = curr.child[idx];
            if (temp == null) {
                curr.child[idx] = new TrieNode();
                curr = curr.child[idx];
            } else {
                curr = temp;
            }
        }
        curr.keyword = word;
    }
}