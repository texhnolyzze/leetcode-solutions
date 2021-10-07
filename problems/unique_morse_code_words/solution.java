class Solution {
    public int uniqueMorseRepresentations(final String[] words) {
        final String[] morseTable = {
            ".-",
            "-...",
            "-.-.",
            "-..",
            ".",
            "..-.",
            "--.",
            "....",
            "..",
            ".---",
            "-.-",
            ".-..",
            "--",
            "-.",
            "---",
            ".--.",
            "--.-",
            ".-.",
            "...",
            "-",
            "..-",
            "...-",
            ".--",
            "-..-",
            "-.--",
            "--.."
        };
        final Set<String> transformations = new HashSet<>();
        for (int i = 0; i < words.length; i++) {
            final String word = words[i];
            final StringBuilder builder = new StringBuilder();
            for (int j = 0; j < word.length(); j++) {
                final char c = word.charAt(j);
                builder.append(morseTable[c - 'a']);
            }
            transformations.add(builder.toString());
        }
        return transformations.size();
    }
}