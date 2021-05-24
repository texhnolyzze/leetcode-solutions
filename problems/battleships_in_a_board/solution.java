class Solution {
    public int countBattleships(final char[][] board) {
        final char ship = 'X';
        final char empty = '.';
        int count = 0;
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
                if (
                    board[y][x] == ship &&
                    (y == 0 || board[y - 1][x] == empty) &&
                    (x == 0 || board[y][x - 1] == empty)
                ) {
                    count++;
                }
            }
        }
        return count;
    }
}