class WordDictionary {

        private final Node root = new Node();
        public WordDictionary() {}

        public void addWord(String word) {
            if (!word.isEmpty())
                addWord(root, word, 0);
        }

        private void addWord(Node n, String word, int i) {
            if (i == word.length()) {
                n.definesWord = true;
                return;
            }
            char c = word.charAt(i);
            int index = c - 'a';
            if (n.childs[index] == null)
                n.childs[index] = new Node();
            addWord(n.childs[index], word, i + 1);
        }

        public boolean search(String word) {
            return search(root, word, 0);
        }

        private boolean search(Node n, String word, int i) {
            if (n == null)
                return false;
            if (i == word.length())
                return n.definesWord;
            char c = word.charAt(i);
            if (c != '.')
                return search(n.childs[c - 'a'], word, i + 1);
            for (Node child : n.childs) {
                boolean find = search(child, word, i + 1);
                if (find)
                    return true;
            }
            return false;
        }
    
    class Node {
        boolean definesWord;
        final Node[] childs = new Node[26];
    }

    }

    

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */