class Solution {
    public int maxLength(final List<String> strings) {
        strings.removeIf(
            s -> s.chars().distinct().count() != s.length()
        );
        final int[] masks = new int[strings.size()];
        for (int i = 0; i < strings.size(); i++) {
            final String s = strings.get(i);
            int mask = 0;
            for (int j = 0; j < s.length(); j++) {
                final int pos = s.charAt(j) - 'a';
                mask |= (1 << pos);
            }
            masks[i] = mask;
        }
        return maxLength(
            masks,
            0,
            0
        );
    }

    private int maxLength(
        final int[] masks,
        final int from,
        final int mask
    ) {
        int max = Integer.bitCount(mask);
        if (from == masks.length) {
            return max;
        }
        final int otherMask = masks[from];
        if ((mask & otherMask) == 0) {
            max = Math.max(
                max,
                maxLength(
                    masks,
                    from + 1,
                    mask | otherMask
                )
            );
        }
        max = Math.max(
            max,
            maxLength(
                masks,
                from + 1,
                mask
            )
        );
        return max;
    }
}