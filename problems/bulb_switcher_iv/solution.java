class Solution {
    public int minFlips(final String target) {
        int ops = 0;
        boolean suffixBitState = false;
        for (int i = 0; i < target.length(); i++) {
            final char c = target.charAt(i);
            if ((c == '0' && !suffixBitState) || (c == '1' && suffixBitState))
                continue;
            ops++;
            suffixBitState = !suffixBitState;
        }
        return ops;
    }
}