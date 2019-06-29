class Solution {
    public String longestCommonPrefix(String[] strs) {
        int n = strs.length;
        Node root = new Node();
        for (String str : strs)
            addToTrie(root, str, 0);
        String lcp = "";
        for (Map.Entry<Character, Node> entry : root.childs.entrySet()) {
            Node child = entry.getValue();
            if (child.numChilds == n) {
                String prefix = findPrefix(child, n, 1, new StringBuilder().append(entry.getKey()));
                if (prefix.length() > lcp.length())
                    lcp = prefix;
            }
        }
        return lcp;
    }
    
    private void addToTrie(Node n, String str, int i) {
        n.numChilds++;
        if (i == str.length()) 
            return;
        Node child = n.childs.get(str.charAt(i));
        if (child == null)
            n.childs.put(str.charAt(i), child = new Node());
        addToTrie(child, str, i + 1);
    }

    private String findPrefix(Node node, int n, int depth, StringBuilder sb) {
        String lcp = sb.toString();
        for (Map.Entry<Character, Node> entry : node.childs.entrySet()) {
            Node child = entry.getValue();
            if (child.numChilds == n) {
                String s = findPrefix(child, n, depth + 1, sb.append(entry.getKey()));
                if (s.length() > lcp.length())
                    lcp = s;
                sb.setLength(depth);
            }
        }
        return lcp;
    }
    
    static class Node {
        int numChilds;
        Map<Character, Node> childs = new HashMap<>(1);
    }
}