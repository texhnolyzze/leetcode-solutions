class Solution {
    public char[][] updateBoard(
        final char[][] board,
        final int[] click
    ) {
        final int m = board.length;
        final int n = board[0].length;
        final int clicky = click[0];
        final int clickx = click[1];
        final char pos = board[clicky][clickx];
        if (pos == 'M') {
            board[clicky][clickx] = 'X';
        } else {
            final BitSet seen = new BitSet(m * n);
            reveal(board, clickx, clicky, seen);
        }
        return board;
    }

    private void reveal(
        final char[][] board,
        final int x,
        final int y,
        final BitSet seen
    ) {
        final int m = board.length;
        final int n = board[0].length;
        if (x < 0 || x >= n || y < 0 || y >= m) {
            return;
        }
        final int idx = y * n + x;
        if (seen.get(idx)) {
            return;
        }
        seen.set(idx);
        final int mines = numMines(board, x, y);
        if (mines > 0) {
            board[y][x] = (char) ('0' + mines);
        } else {
            board[y][x] = 'B';
            reveal(board, x, y - 1, seen);
            reveal(board, x + 1, y - 1, seen);
            reveal(board, x + 1, y, seen);
            reveal(board, x + 1, y + 1, seen);
            reveal(board, x, y + 1, seen);
            reveal(board, x - 1, y + 1, seen);
            reveal(board, x - 1, y, seen);
            reveal(board, x - 1, y - 1, seen);
        }
    }

    private int numMines(
        final char[][] board,
        final int x,
        final int y
    ) {
        int res = 0;
        res += mine(board, x, y - 1);
        res += mine(board, x + 1, y - 1);
        res += mine(board, x + 1, y);
        res += mine(board, x + 1, y + 1);
        res += mine(board, x, y + 1);
        res += mine(board, x - 1, y + 1);
        res += mine(board, x - 1, y);
        res += mine(board, x - 1, y - 1);
        return res;
    }

    private int mine(
        final char[][] board,
        final int x,
        final int y
    ) {
        final int m = board.length;
        final int n = board[0].length;
        if (x < 0 || x >= n || y < 0 || y >= m) {
            return 0;
        }
        return board[y][x] == 'M' ? 1 : 0;
    }
}