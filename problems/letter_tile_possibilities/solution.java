class Solution {
    private final Set<String> seen = new HashSet<>();

    public int numTilePossibilities(final String tiles) {
        seen.clear();
        return dfs(tiles, 0, new StringBuilder(tiles.length()));
    }

    private int dfs(
        final String tiles,
        final int used,
        final StringBuilder builder
    ) {
        int res = 0;
        for (int i = 0; i < tiles.length(); i++) {
            if ((used & (1 << i)) != 0) {
                continue;
            }
            final int prevLen = builder.length();
            builder.append(tiles.charAt(i));
            final String str = builder.toString();
            if (!seen.contains(str)) {
                seen.add(str);
                res++;
                res += dfs(tiles, used | (1 << i), builder);
            }
            builder.setLength(prevLen);
        }
        return res;
    }
}