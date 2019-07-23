class Solution {
    public static boolean isValidSerialization(String preorder) {
        String[] tokens = preorder.split(",");
        int i = dfs(tokens, 0);
        return i == tokens.length;
    }

    private static int dfs(String[] preorder, int i) {
        if (i >= preorder.length)
            return -1;
        String s = preorder[i];
        if (s.equals("#"))
            return i + 1;
        int left = dfs(preorder, i + 1);
        if (left == -1)
            return -1;
        int right = dfs(preorder, left);
        return right;
    }
}