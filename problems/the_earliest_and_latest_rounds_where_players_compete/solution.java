class Solution {
    private int earliest;
    private int latest;
    private int firstPlayer;
    private int secondPlayer;
    private boolean[] players;

    public int[] earliestAndLatest(
        final int n,
        final int firstPlayer,
        final int secondPlayer
    ) {
        this.earliest = Integer.MAX_VALUE;
        this.latest = Integer.MIN_VALUE;
        this.firstPlayer = firstPlayer - 1;
        this.secondPlayer = secondPlayer - 1;
        this.players = new boolean[n];
        Arrays.fill(players, true);
        earliestAndLatest0(
            0,
            n - 1,
            1
        );
        return new int[] {
            earliest, latest
        };
    }

    private void earliestAndLatest0(
        final int from,
        final int to,
        final int round
    ) {
        if (from >= to) {
            earliestAndLatest0(0, players.length - 1, round + 1);
        } else {
            final int leftmost = leftmost(from, to, players);
            if (leftmost == -1) {
                earliestAndLatest0(0, players.length - 1, round + 1);
            } else {
                final int rightmost = rightmost(to, from, players);
                if (leftmost == firstPlayer && rightmost == secondPlayer) {
                    earliest = Math.min(earliest, round);
                    latest = Math.max(latest, round);
                } else if (leftmost == rightmost) {
                    earliestAndLatest0(0, players.length - 1, round + 1);
                } else {
                    if (firstPlayer == leftmost || secondPlayer == leftmost) {
                        players[rightmost] = false;
                        earliestAndLatest0(leftmost + 1, rightmost - 1, round);
                        players[rightmost] = true;
                    } else if (secondPlayer == rightmost || firstPlayer == rightmost) {
                        players[leftmost] = false;
                        earliestAndLatest0(leftmost + 1, rightmost - 1,  round);
                        players[leftmost] = true;
                    } else {
                        players[leftmost] = false;
                        earliestAndLatest0(leftmost + 1, rightmost - 1, round);
                        players[leftmost] = true;
                        players[rightmost] = false;
                        earliestAndLatest0(leftmost + 1, rightmost - 1, round);
                        players[rightmost] = true;
                    }
                }
            }
        }
    }

    private int leftmost(
        final int from,
        final int to,
        final boolean[] players
    ) {
        for (int i = from; i <= to; i++) {
            if (players[i]) {
                return i;
            }
        }
        return -1;
    }

    private int rightmost(
        final int to,
        final int from,
        final boolean[] players
    ) {
        for (int i = to; i >= from; i--) {
            if (players[i]) {
                return i;
            }
        }
        return -1;
    }
}