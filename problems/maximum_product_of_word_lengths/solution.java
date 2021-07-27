class Solution {
    public int maxProduct(final String[] words) {
        final int[] mask = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            final String word = words[i];
            final int len = word.length();
            int m = 0;
            for (int j = 0; j < len; j++) {
                final char c = word.charAt(j);
                m = m | (1 << (c - 'a'));
            }
            mask[i] = m;
        }
        int max = 0;
        for (int i = 0; i < mask.length - 1; i++) {
            final int left = mask[i];
            for (int j = i + 1; j < mask.length; j++) {
                final int right = mask[j];
                if ((left ^ right) == (left | right)) {
                    max = Math.max(max, words[i].length() * words[j].length());
                }
            }
        }
        return max;
    }
}