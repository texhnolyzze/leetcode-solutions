class Solution {
    public boolean exist(
        final char[][] board,
        final String word
    ) {
        final char firstSymbol = word.charAt(0);
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
                if (board[y][x] == firstSymbol) {
                    visited[y][x] = true;
                    if (
                        exist(
                            board,
                            word,
                            1,
                            y,
                            x,
                            visited
                        )
                    ) {
                        return true;
                    }
                    visited[y][x] = false;
                }
            }
        }
        return false;
    }

    private boolean exist(
        final char[][] board,
        final String word,
        final int nextSymbolIdx,
        final int y,
        final int x,
        final boolean[][] visited
    ) {
        if (nextSymbolIdx == word.length()) {
            return true;
        } else {
            final char nextSymbol = word.charAt(nextSymbolIdx);
            if (x > 0 && !visited[y][x - 1]) {
                final char leftSymbol = board[y][x - 1];
                if (leftSymbol == nextSymbol) {
                    visited[y][x - 1] = true;
                    if (exist(board, word, nextSymbolIdx + 1, y, x - 1, visited)) {
                        return true;
                    }
                    visited[y][x - 1] = false;
                }
            }
            if (x < board[0].length - 1 && !visited[y][x + 1]) {
                final char rightSymbol = board[y][x + 1];
                if (rightSymbol == nextSymbol) {
                    visited[y][x + 1] = true;
                    if (exist(board, word, nextSymbolIdx + 1, y, x + 1, visited)) {
                        return true;
                    }
                    visited[y][x + 1] = false;
                }
            }
            if (y > 0 && !visited[y - 1][x]) {
                final char topSymbol = board[y - 1][x];
                if (topSymbol == nextSymbol) {
                    visited[y - 1][x] = true;
                    if (exist(board, word, nextSymbolIdx + 1, y - 1, x, visited)) {
                        return true;
                    }
                    visited[y - 1][x] = false;
                }
            }
            if (y < board.length - 1 && !visited[y + 1][x]) {
                final char bottomSymbol = board[y + 1][x];
                if (bottomSymbol == nextSymbol) {
                    visited[y + 1][x] = true;
                    if (exist(board, word, nextSymbolIdx + 1, y + 1, x, visited)) {
                        return true;
                    }
                    visited[y + 1][x] = false;
                }
            }
            return false;
        }
    }
}