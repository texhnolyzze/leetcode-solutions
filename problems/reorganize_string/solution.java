class Solution {
    public String reorganizeString(final String s) {
        final int[] freq = new int[26];
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
        }
        final PriorityQueue<Character> queue = new PriorityQueue<>(
            Comparator.comparingInt(c -> -freq[c - 'a'])
        );
        final StringBuilder builder = new StringBuilder();
        while (builder.length() != s.length()) {
            for (int i = 0; i < 26; i++) {
                if (freq[i] > 0) {
                    queue.add((char) ('a' + i));
                }
            }
            char next = queue.remove();
            if (builder.length() > 0 && next == builder.charAt(builder.length() - 1)) {
                if (queue.isEmpty()) {
                    return "";
                }
                next = queue.remove();
            }
            builder.append(next);
            freq[next - 'a']--;
            queue.clear();
        }
        return builder.toString();
    }
}