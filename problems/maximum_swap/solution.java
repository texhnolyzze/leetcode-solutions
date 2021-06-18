class Solution {
    public int maximumSwap(int num) {
        int max = num;
        final String str = String.valueOf(num);
        final StringBuilder builder = new StringBuilder(str);
        for (int i = 0; i < str.length() - 1; i++) {
            final char dig1 = str.charAt(i);
            for (int j = i + 1; j < str.length(); j++) {
                final char dig2 = str.charAt(j);
                builder.setCharAt(i, dig2);
                builder.setCharAt(j, dig1);
                final int candidate = Integer.parseInt(builder, 0, str.length(), 10);
                max = Math.max(max, candidate);
                builder.setCharAt(j, dig2);
                builder.setCharAt(i, dig1);
            }
        }
        return max;
    }
}