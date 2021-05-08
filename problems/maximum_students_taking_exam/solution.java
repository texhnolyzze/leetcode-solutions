class Solution {
    public int maxStudents(char[][] seats) {
        final int[] emptyRow = {0};
        return Arrays.stream(
            maxStudents(
                seats,
                0,
                emptyRow
            )
        ).max().orElseThrow();
    }

    private int[] maxStudents(
        final char[][] seats,
        final int rowIndex,
        final int[] previousRows
    ) {
        if (rowIndex == seats.length)
            return previousRows;
        else {
            final int[] currentRows = getBestRows(seats, rowIndex, previousRows);
            return maxStudents(seats, rowIndex + 1, currentRows);
        }
    }

    private int[] getBestRows(
        final char[][] seats,
        final int rowIndex,
        final int[] previousRows
    ) {
        final int[] result = new int[1 << seats[0].length];
        Arrays.fill(result, -1);
        for (int previousRow = 0; previousRow < previousRows.length; previousRow++) {
            if (previousRows[previousRow] == -1)
                continue;
            final int[] permutes = permute(
                seats,
                rowIndex,
                previousRow,
                previousRows[previousRow]
            );
            for (int j = 0; j < permutes.length; j++) {
                if (permutes[j] == -1)
                    continue;
                if (result[j] == -1 || result[j] < permutes[j]) {
                    result[j] = permutes[j];
                }
            }
        }
        return result;
    }

    private int[] permute(
        final char[][] seats,
        final int rowIndex,
        final int previousRow,
        final int previousRowSeatsTaken
    ) {
        final int[] result = new int[1 << seats[0].length];
        Arrays.fill(result, -1);
        permute(seats, rowIndex, previousRow, previousRowSeatsTaken, 0, seats[0].length - 1, result);
        return result;
    }

    private void permute(
        final char[][] seats,
        final int rowIndex,
        final int previousRow,
        final int previousRowSeatsTaken,
        final int currentRow,
        final int seatIndex,
        final int[] result
    ) {
        if (seatIndex == -1) {
            result[currentRow] = previousRowSeatsTaken + Integer.bitCount(currentRow);
        } else {
            final boolean leftSitTaken = seatIndex != seats[0].length - 1 && bit(currentRow, seatIndex + 1) == 1;
            final boolean bottomLeftSitTaken = seatIndex != seats[0].length - 1 && bit(previousRow, seatIndex + 1) == 1;
            final boolean bottomRightSitTaken = seatIndex != 0 && bit(previousRow, seatIndex - 1) == 1;
            if (
                seats[rowIndex][seatIndex] != '#' &&
                !leftSitTaken &&
                !bottomLeftSitTaken &&
                !bottomRightSitTaken
            ) {
                permute(seats, rowIndex, previousRow, previousRowSeatsTaken, setBit(currentRow, seatIndex), seatIndex - 1, result);
                permute(seats, rowIndex, previousRow, previousRowSeatsTaken, currentRow, seatIndex - 1, result);
            } else {
                permute(seats, rowIndex, previousRow, previousRowSeatsTaken, currentRow, seatIndex - 1, result);
            }
        }
    }

    private int bit(
        final int n,
        final int bitIndex
    ) {
        return (n >>> bitIndex) & 1;
    }

    private int setBit(
        final int n,
        final int bitIndex
    ) {
        return n | (1 << bitIndex);
    }
}