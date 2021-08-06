class Solution {
    public int[] prisonAfterNDays(
        final int[] cells,
        final int n
    ) {
        int state = 0;
        for (int i = 0; i < 8; i++) {
            state = state | (cells[i] << (8 - i - 1));
        }
        state = emulate(state, 1);
        if (n != 1) {
            final int[] states = new int[14];
            for (int i = 0; i < 14; i++) {
                states[i] = state;
                state = emulate(state, 1);
            }
            state = states[(n - 1) % 14];
        }
        for (int i = 0; i < 8; i++) {
            if ((state & 1 << 8 - i - 1) != 0) {
                cells[i] = 1;
            } else {
                cells[i] = 0;
            }
        }
        return cells;
    }

    private int emulate(final int initialState, final int days) {
        final int mask = 0b01111110;
        int yesterday = initialState;
        int today = 0;
        for (int day = 0; day < days; day++) {
            for (int cellIdx = 0; cellIdx < 8; cellIdx++) {
                if (cellIdx == 0 || cellIdx == 7) {
                    today = today & mask;
                } else {
                    if ((yesterday & 1 << cellIdx + 1) != 0 ^ (yesterday & 1 << cellIdx - 1) != 0) {
                        today = today & (mask & ~(1 << cellIdx));
                    } else {
                        today = today | (1 << cellIdx);
                    }
                }
            }
            final int temp = yesterday;
            yesterday = today;
            today = temp;
        }
        return yesterday;
    }
}