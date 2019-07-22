class Solution {
    public boolean isValidSudoku(char[][] board) {
        boolean[][] isSetted = new boolean[9][9];
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                char c = board[row][col];
                if (c == '.')
                    continue;
                int digit = board[row][col] - '0';
                if (isSetted[row][digit - 1])
                    return false;
                isSetted[row][digit - 1] = true;
            }
        }
        for (int i = 0; i < 9; i++) Arrays.fill(isSetted[i], false);
        for (int col = 0; col < 9; col++) {
            for (int row = 0; row < 9; row++) {
                char c = board[row][col];
                if (c == '.')
                    continue;
                int digit = board[row][col] - '0';
                if (isSetted[col][digit - 1])
                    return false;
                isSetted[col][digit - 1] = true;
            }
        }
        for (int i = 0; i < 3; i++) { // validate each subsquare
            for (int j = 0; j < 3; j++) {
                Arrays.fill(isSetted[0], false);
                for (int row = i * 3; row < i * 3 + 3; row++) {
                    for (int col = j * 3; col < j * 3 + 3; col++) {
                        char c = board[row][col];
                        if (c == '.')
                            continue;
                        int digit = board[row][col] - '0';
                        if (isSetted[0][digit - 1])
                            return false;
                        isSetted[0][digit - 1] = true;
                    }
                }
            }
        }
        return true;
    }
}