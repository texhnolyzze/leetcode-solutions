class Solution {
    private char[] str;
    private final char[][] temp1 = new char[30][];
    private final char[][] temp2 = new char[30][];
    private final boolean[] tempFlags = new boolean[26];
    private final Map<MemoKey, Boolean> memtable = new HashMap<>();

    public boolean isScramble(final String s1, final String s2) {
        memtable.clear();
        str = new char[s1.length()];
        for (int i = 0; i < s1.length(); i++) {
            str[i] = s1.charAt(i);
        }
        for (int depth = 0; depth < temp1.length; depth++) {
            temp1[depth] = new char[str.length];
            temp2[depth] = new char[str.length];
        }
        return isScramble(s2, 0, str.length - 1, 0);
    }

    private boolean isScramble(
        final String s,
        final int left,
        final int right,
        final int depth
    ) {
        if (!countsMatch(s, left, right))
            return false;
        if (eq(s, left, right)) {
            return true;
        } else {
            final int len = right - left + 1;
            if (len <= 1)
                return false;
            final char[] keyChars = new char[len];
            System.arraycopy(str, left, keyChars, 0, right + 1 - left);
            MemoKey key = new MemoKey(keyChars, left, right);
            final Boolean memo = memtable.get(key);
            if (memo != null)
                return memo;
            boolean isScramble = false;
            final char[] tempDepth1 = temp1[depth];
            final char[] tempDepth2 = temp2[depth];
            System.arraycopy(str, left, tempDepth2, 0, len);
            for (int divisionIdx = left + 1; divisionIdx <= right; divisionIdx++) {
                int leftLen = divisionIdx - left;
                int rightLen = right - divisionIdx + 1;
                System.arraycopy(str, left, tempDepth1, 0, leftLen);
                final boolean leftEqIfNotSwap = isScramble(s, left, divisionIdx - 1, depth + 1);
                final boolean rightEqIfNotSwap = leftEqIfNotSwap && isScramble(s, divisionIdx, right, depth + 1);
                System.arraycopy(str, divisionIdx, str, left, rightLen);
                for (int i = left + rightLen, j = 0; i <= right; i++, j++) {
                    str[i] = tempDepth1[j];
                }
                if (
                    (leftEqIfNotSwap && rightEqIfNotSwap) ||
                    (isScramble(s, left, left + rightLen - 1, depth + 1) && isScramble(s, left + rightLen, right, depth + 1))
                ) {
                    System.arraycopy(tempDepth2, 0, str, left, len);
                    isScramble = true;
                    break;
                }
                System.arraycopy(tempDepth2, 0, str, left, len);
            }
            memtable.put(key, isScramble);
            return isScramble;
        }
    }

    private boolean countsMatch(final String s, final int left, final int right) {
        Arrays.fill(tempFlags, false);
        for (int i = left; i <= right; i++) {
            final char c = s.charAt(i);
            if (tempFlags[c - 'a'])
                continue;
            int targetCount = 1;
            for (int j = i + 1; j <= right; j++) {
                if (s.charAt(j) == c)
                    targetCount++;
            }
            int actualCount = 0;
            for (int j = left; j <= right; j++) {
                if (str[j] == c)
                    actualCount++;
            }
            if (actualCount != targetCount)
                return false;
            tempFlags[c - 'a'] = true;
        }
        return true;
    }

    private boolean eq(final String s, final int left, final int right) {
        for (int i = left; i <= right; i++) {
            if (str[i] != s.charAt(i))
                return false;
        }
        return true;
    }

    private static class MemoKey {

        private final char[] str;
        private final int left;
        private final int right;

        private MemoKey(final char[] str, final int left, final int right) {
            this.str = str;
            this.left = left;
            this.right = right;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            final MemoKey key = (MemoKey) o;
            return left == key.left && right == key.right && Arrays.equals(str, key.str);
        }

        @Override
        public int hashCode() {
            int result = Arrays.hashCode(str);
            result = 31 * result + left;
            result = 31 * result + right;
            return result;
        }

    }
}