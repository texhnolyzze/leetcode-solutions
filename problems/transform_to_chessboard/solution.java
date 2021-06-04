class Solution {
    private int res;

    public int movesToChessboard(final int[][] board) {
        res = Integer.MAX_VALUE;
        tryChessBoard(board, 0);
        tryChessBoard(board, 1);
        return res == Integer.MAX_VALUE ? -1 : res;
    }


    private void tryChessBoard(final int[][] board, final int topLeftCorner) {
        final int size = board.length;
        final int[][] chessBoard = createChessBoard(size, topLeftCorner);
        final int[][] xor = xor(board, size, chessBoard);
        final boolean[] swappedRows = new boolean[size];
        final boolean[] swappedColumns = new boolean[size];
        final int numOnes = numOnes(xor);
        dfs(xor, swappedRows, swappedColumns, numOnes, 0);
    }

    private void dfs(
        final int[][] xor,
        final boolean[] swappedRows,
        final boolean[] swappedColumns,
        final int numOnes,
        final int numOps
    ) {
        if (numOnes == 0) {
            res = Math.min(res, numOps);
        } else {
            if (res <= numOps)
                return;
            final int size = xor.length;
            Set<BitSet> seenRows = new HashSet<>();
            Set<BitSet> seenColumns = new HashSet<>();
            for (int y = 0; y < size; y++) {
                for (int x = 0; x < size; x++) {
                    boolean found = false;
                    if (xor[y][x] == 1) {
                        if (!swappedRows[y]) {
                            for (int row = y + 1; row < size; row += 2) {
                                if (swappedRows[row])
                                    continue;
                                boolean same = true;
                                int ones = 0;
                                BitSet bitSet = new BitSet(size);
                                for (int col = 0; col < size; col++) {
                                    if (xor[y][col] != xor[row][col]) {
                                        same = false;
                                        break;
                                    }
                                    if (xor[row][col] == 1) {
                                        bitSet.set(col);
                                        ones -= 2;
                                    } else {
                                        ones += 2;
                                    }
                                }
                                if (same && !seenRows.contains(bitSet)) {
                                    seenRows.add(bitSet);
                                    found = true;
                                    swappedRows[y] = true;
                                    swappedRows[row] = true;
                                    for (int col = 0; col < size; col++) {
                                        xor[y][col] = (xor[y][col] + 1) % 2;
                                        xor[row][col] = (xor[row][col] + 1) % 2;
                                    }
                                    dfs(xor, swappedRows, swappedColumns, numOnes + ones, numOps + 1);
                                    for (int col = 0; col < size; col++) {
                                        xor[y][col] = (xor[y][col] + 1) % 2;
                                        xor[row][col] = (xor[row][col] + 1) % 2;
                                    }
                                    swappedRows[y] = false;
                                    swappedRows[row] = false;
                                }
                            }
                        }
                        if (!swappedColumns[x]) {
                            for (int col = x + 1; col < size; col += 2) {
                                if (swappedColumns[col])
                                    continue;
                                boolean same = true;
                                int ones = 0;
                                BitSet bitSet = new BitSet(size);
                                for (int row = 0; row < size; row++) {
                                    if (xor[row][x] != xor[row][col]) {
                                        same = false;
                                        break;
                                    }
                                    if (xor[row][x] == 1) {
                                        ones -= 2;
                                        bitSet.set(row);
                                    } else {
                                        ones += 2;
                                    }
                                }
                                if (same && !seenColumns.contains(bitSet)) {
                                    seenColumns.add(bitSet);
                                    found = true;
                                    swappedColumns[x] = true;
                                    swappedColumns[col] = true;
                                    for (int row = 0; row < size; row++) {
                                        xor[row][x] = (xor[row][x] + 1) % 2;
                                        xor[row][col] = (xor[row][col] + 1) % 2;
                                    }
                                    dfs(xor, swappedRows, swappedColumns, numOnes + ones, numOps + 1);
                                    for (int row = 0; row < size; row++) {
                                        xor[row][x] = (xor[row][x] + 1) % 2;
                                        xor[row][col] = (xor[row][col] + 1) % 2;
                                    }
                                    swappedColumns[x] = false;
                                    swappedColumns[col] = false;
                                }
                            }
                        }
                        if (!found) {
                            return;
                        }
                    }
                }
            }
        }
    }

    private int[][] xor(final int[][] board, final int size, final int[][] chessBoard) {
        final int[][] xor = new int[size][size];
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                xor[y][x] = board[y][x] ^ chessBoard[y][x];
            }
        }
        return xor;
    }

    private int numOnes(final int[][] arr) {
        int numOnes = 0;
        for (int y = 0; y < arr.length; y++) {
            for (int x = 0; x < arr.length; x++) {
                numOnes += arr[y][x];
            }
        }
        return numOnes;
    }

    private int[][] createChessBoard(final int size, final int topLeftCorner) {
        final int[][] board = new int[size][size];
        int prev = topLeftCorner;
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                board[y][x] = prev;
                prev = (prev + 1) % 2;
            }
            if (size % 2 == 0)
                prev = (prev + 1) % 2;
        }
        return board;
    }
}