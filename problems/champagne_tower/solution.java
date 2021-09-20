class Solution {
    public double champagneTower(
        final int poured,
        final int queryRow,
        final int queryGlass
    ) {
        if (queryRow == 0) {
            return Math.min(1, poured);
        }
        if (poured <= 1) {
            return 0.0;
        }
        final ChampagneGlass[][] memtable = new ChampagneGlass[queryRow][];
        for (int i = 0; i < memtable.length; i++) {
            memtable[i] = new ChampagneGlass[i + 1];
        }
        final double topPoured = (double) (poured - 1) / 2;
        memtable[0][0] = new ChampagneGlass(
            topPoured,
            topPoured
        );
        if (queryGlass == 0) {
            final ChampagneGlass topRight = champagneTower(queryRow - 1, 0, memtable);
            return Math.min(1, topRight.pouredLeft);
        } else if (queryGlass == queryRow) {
            final ChampagneGlass topLeft = champagneTower(queryRow - 1, queryRow - 1, memtable);
            return Math.min(1, topLeft.pouredRight);
        } else {
            final ChampagneGlass topLeft = champagneTower(queryRow - 1, queryGlass - 1, memtable);
            final ChampagneGlass topRight = champagneTower(queryRow - 1, queryGlass, memtable);
            return Math.min(
                1,
                topLeft.pouredRight + topRight.pouredLeft
            );
        }
    }

    private ChampagneGlass champagneTower(
        final int queryRow,
        final int queryGlass,
        final ChampagneGlass[][] memtable
    ) {
        ChampagneGlass res = memtable[queryRow][queryGlass];
        if (res != null) {
            return res;
        }
        final double poured;
        if (queryGlass == 0) {
            final ChampagneGlass topRight = champagneTower(queryRow - 1, 0, memtable);
            poured = Math.max(0, topRight.pouredLeft - 1);
        } else if (queryGlass == queryRow) {
            final ChampagneGlass topLeft = champagneTower(queryRow - 1, queryRow - 1, memtable);
            poured = Math.max(0, topLeft.pouredRight - 1);
        } else {
            final ChampagneGlass topLeft = champagneTower(queryRow - 1, queryGlass - 1, memtable);
            final ChampagneGlass topRight = champagneTower(queryRow - 1, queryGlass, memtable);
            poured = Math.max(0, topLeft.pouredRight + topRight.pouredLeft - 1);
        }
        res = new ChampagneGlass(
            poured / 2,
            poured / 2
        );
        memtable[queryRow][queryGlass] = res;
        return res;
    }
    
    private static class ChampagneGlass {

        final double pouredLeft;
        final double pouredRight;

        private ChampagneGlass(
            final double pouredLeft,
            final double pouredRight
        ) {
            this.pouredLeft = pouredLeft;
            this.pouredRight = pouredRight;
        }

    }
}