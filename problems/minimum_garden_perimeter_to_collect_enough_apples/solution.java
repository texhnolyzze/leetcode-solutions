class Solution {
    public long minimumPerimeter(final long neededApples) {
        long l = 2;
        long r;
        for (long side = 2;; side = 2 * side) {
            final long numApples = numApples(side);
            if (numApples >= neededApples) {
                r = side;
                break;
            }
        }
        while (l <= r) {
            final long mid = l + (r - l) / 2;
            final long numApples = numApples(mid);
            if (numApples < neededApples) {
                l = mid + 1;
            } else if (numApples > neededApples) {
                if (numApples(mid - 1) < neededApples) {
                    return 4 * (mid % 2 == 0 ? mid : mid + 1);
                } else {
                    r = mid - 1;
                }
            } else {
                if (mid % 2 == 0) {
                    return 4 * mid;
                } else {
                    if (numApples(mid - 1) < neededApples) {
                        return 4 * (mid + 1);
                    } else {
                        return 4 * (mid - 1);
                    }
                }
            }
        }
        throw new IllegalStateException();
    }

    private long numApples(final long n) {
        if (n < 2) {
            return 0;
        }
        return n * (n + 1) * (n + 2) / 2;
    }
}