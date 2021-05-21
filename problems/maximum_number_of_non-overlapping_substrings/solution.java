class Solution {
    public List<String> maxNumOfSubstrings(String str) {
        int[] lo = new int[26];
        int[] hi = new int[26];
        Arrays.fill(lo, -1);
        Arrays.fill(hi, -1);
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (lo[c - 'a'] == -1) {
                lo[c - 'a'] = i;
            }
            hi[c - 'a'] = i;
        }
        List<String> result = new ArrayList<>();
        final boolean[] seen = new boolean[lo.length];
        while (Arrays.stream(lo).anyMatch(i -> i != -1)) {
            int targetLen = Integer.MAX_VALUE;
            int targetLo = Integer.MAX_VALUE;
            int targetHi = -1;
            for (char candidate = 'a'; candidate - 'a' < lo.length; candidate++) {
                if (lo[candidate - 'a'] == -1)
                    continue;
                int candidateLo = lo[candidate - 'a'];
                int candidateHi = hi[candidate - 'a'];
                for (char other = 'a'; other - 'a' < lo.length;) {
                    if (
                        other == candidate ||
                        lo[other - 'a'] == -1 ||
                        seen[other - 'a'] ||
                        !(
                            intersects(
                                candidateLo,
                                candidateHi,
                                lo[other - 'a'],
                                hi[other - 'a']
                            ) && str.indexOf(other, candidateLo) < candidateHi
                        )
                    ) {
                        other++;
                    } else {
                        candidateLo = Math.min(candidateLo, lo[other - 'a']);
                        candidateHi = Math.max(candidateHi, hi[other - 'a']);
                        seen[other - 'a'] = true;
                        other = 'a';
                    }
                }
                Arrays.fill(seen, false);
                int candidateLen = candidateHi - candidateLo + 1;
                if (candidateLen < targetLen) {
                    targetLen = candidateLen;
                    targetLo = candidateLo;
                    targetHi = candidateHi;
                }
            }
            result.add(str.substring(targetLo, targetHi + 1));
            invalidateAll(str, lo, hi, targetLo, targetHi, seen);
            Arrays.fill(seen, false);
        }
        return result;
    }

    private void invalidateAll(
        final String str,
        final int[] lo,
        final int[] hi,
        final int targetLo,
        final int targetHi,
        final boolean[] seen
    ) {
        for (char c = 'a'; c - 'a' < lo.length; c++) {
            if (lo[c - 'a'] == -1)
                continue;
            if (intersects(targetLo, targetHi, lo[c - 'a'], hi[c - 'a'])) {
                invalidateAll(str, lo, hi, c, seen);
            }
        }
    }

    private void invalidateAll(
        final String str,
        final int[] lo,
        final int[] hi,
        final char targetChar,
        final boolean[] seen
    ) {
        seen[targetChar - 'a'] = true;
        for (char c = 'a'; c - 'a' < lo.length; c++) {
            if (
                seen[c - 'a'] ||
                targetChar == c ||
                lo[c - 'a'] == -1 ||
                !intersects(
                    lo[targetChar - 'a'],
                    hi[targetChar - 'a'],
                    lo[c - 'a'],
                    hi[c - 'a']
                )
            ) {
                continue;
            }
            final int c1IndexAfterC2 = str.indexOf(targetChar, lo[c - 'a']);
            if (c1IndexAfterC2 >= 0 && c1IndexAfterC2 <= hi[c - 'a']) {
                invalidateAll(str, lo, hi, c, seen);
            }
        }
        lo[targetChar - 'a'] = -1;
        hi[targetChar - 'a'] = -1;
    }

    private boolean intersects(int a, int b, int c, int d) {
        return Math.max(a, c) <= Math.min(b, d);
    }
}