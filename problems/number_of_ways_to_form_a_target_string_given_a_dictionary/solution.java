class Solution {
    public int numWays(String[] words, String target) {
        return numWays(
            words,
            target,
            0,
            0,
            createMemtable(target, words),
            indexWords(words)
        );
    }

    private TreeMap<Integer, Integer>[] indexWords(final String[] words) {
        TreeMap<Integer, Integer>[] index = new TreeMap[26];
        for (int i = 0; i < index.length; i++) {
            index[i] = new TreeMap<>();
        }
        for (String word : words) {
            final int len = word.length();
            for (int i = 0; i < len; i++) {
                char c = word.charAt(i);
                index[c - 'a'].compute(i, (unused, count) -> count == null ? 1 : count + 1);
            }
        }
        return index;
    }

    private int numWays(
        final String[] words,
        final String target,
        final int targetCharIndex,
        final int validCharIdx,
        final int[][] memtable,
        final TreeMap<Integer, Integer>[] index
    ) {
        if (validCharIdx >= words[0].length())
            return 0;
        int res = memtable[targetCharIndex][validCharIdx];
        if (res != -1)
            return res;
        final int charactersRemained = target.length() - targetCharIndex;
        res = 0;
        char c = target.charAt(targetCharIndex);
        TreeMap<Integer, Integer> targetCharPositionalFrequency = index[c - 'a'];
        if (targetCharIndex == target.length() - 1) {
            for (final Map.Entry<Integer, Integer> entry : targetCharPositionalFrequency.tailMap(validCharIdx, true).entrySet()) {
                res += entry.getValue();
            }
        } else {
            for (final Map.Entry<Integer, Integer> entry : targetCharPositionalFrequency.subMap(validCharIdx, true, words[0].length() - charactersRemained, true).entrySet()) {
                int ways = numWays(words, target, targetCharIndex + 1, entry.getKey() + 1, memtable, index);
                res = (res + moduloMultiplication(ways, entry.getValue(), 1000000007)) % 1000000007;
            }
        }
        memtable[targetCharIndex][validCharIdx] = res;
        return res;
    }

    private int moduloMultiplication(int a, int b, int mod) {
        int res = 0;
        a %= mod;
        while (b != 0) {
            if ((b & 1) == 1)
                res = (res + a) % mod;
            a = (2 * a) % mod;
            b >>= 1;
        }
        return res;
    }

    private int[][] createMemtable(String target, String[] words) {
        int[][] memtable = new int[target.length()][words[0].length()];
        for (int i = 0; i < memtable.length; i++) {
            Arrays.fill(memtable[i], -1);
        }
        return memtable;
    }
}