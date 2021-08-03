class Solution {
    public boolean canJump(final int[] jumpLength) {
        int pos = 0;
        final int last = jumpLength.length - 1;
        while (true) {
            if (pos == last) {
                return true;
            }
            final int maxLength = jumpLength[pos];
            if (maxLength == 0) {
                return false;
            }
            int candidateJumpLen = -1;
            int candidatePos = -1;
            for (int i = 1; i <= maxLength; i++) {
                final int nextPos = pos + i;
                if (nextPos >= jumpLength.length) {
                    break;
                }
                if (nextPos == last) {
                    return true;
                }
                final int posJumpLen = jumpLength[nextPos];
                if (
                    candidatePos == -1 ||
                    (candidatePos + candidateJumpLen) < (nextPos + posJumpLen)
                ) {
                    candidateJumpLen = posJumpLen;
                    candidatePos = nextPos;
                }
            }
            if (candidatePos == -1) {
                return false;
            }
            pos = candidatePos;
        }
    }
}