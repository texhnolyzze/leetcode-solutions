class WordFilter {

    private final Trie trie;

        public WordFilter(String[] dictionary) {
            this.trie = new Trie(dictionary);
        }

        public int f(String prefix, String suffix) {
            return trie.getIndex(prefix, suffix);
        }

        private static class Trie {

            private final Node root = new Node();
            private final char[] tempChars = new char[10];
            private int depth;
            private int maxIdxSoFar;
            {
                root.wordIndex = -1;
            }

            Trie(String[] dictionary) {
                for (int i = 0; i < dictionary.length; i++) {
                    add(dictionary[i], i);
                }
            }

            void add(
                final String word,
                final int wordIdx
            ) {
                depth = 0;
                add(root, word, wordIdx);
            }

            private void add(
                final Node node,
                final String word,
                final int wordIdx
            ) {
                if (depth == word.length()) {
                    node.wordIndex = wordIdx;
                } else {
                    char c = word.charAt(depth);
                    if (node.child == null)
                        node.initChild();
                    Node next = node.child[c - 'a'];
                    node.child[c - 'a'] = next;
                    depth++;
                    add(next, word, wordIdx);
                }
            }

            int getIndex(String prefix, String suffix) {
                depth = 0;
                maxIdxSoFar = -1;
                Node node = locatePrefix(prefix, root);
                if (node == null)
                    return -1;
                findMaxIdx(suffix, node);
                return maxIdxSoFar;
            }

            private Node locatePrefix(String prefix, Node node) {
                if (depth == prefix.length())
                    return node;
                else {
                    if (node.child == null)
                        return null;
                    char c = prefix.charAt(depth);
                    tempChars[this.depth++] = c;
                    return locatePrefix(prefix, node.child[c - 'a']);
                }
            }

            private void findMaxIdx(String suffix, Node root) {
                if (root.wordIndex != -1 && tempCharsEndsWith(suffix)) {
                    maxIdxSoFar = Math.max(maxIdxSoFar, root.wordIndex);
                }
                if (root.child != null) {
                    for (int i = 0; i < root.child.length; i++) {
                        Node next = root.child[i];
                        char c = (char) ('a' + i);
                        tempChars[depth++] = c;
                        findMaxIdx(suffix, next);
                        depth--;
                    }
                }
            }

            private boolean tempCharsEndsWith(String suffix) {
                if (depth < suffix.length())
                    return false;
                for (int i = depth - suffix.length(), j = 0; i < depth; i++, j++) {
                    char c1 = tempChars[i];
                    char c2 = suffix.charAt(j);
                    if (c1 != c2)
                        return false;
                }
                return true;
            }

            private static class Node {

                int wordIndex = -1;
                Node[] child;

                void initChild() {
                    Node[] child = new Node[26];
                    for (int i = 0; i < child.length; i++) {
                        Node node = new Node();
                        node.wordIndex = -1;
                        child[i] = node;
                    }
                    this.child = child;
                }

            }

        }
}

/**
 * Your WordFilter object will be instantiated and called as such:
 * WordFilter obj = new WordFilter(words);
 * int param_1 = obj.f(prefix,suffix);
 */