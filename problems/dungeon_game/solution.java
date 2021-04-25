import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Solution {

    public int calculateMinimumHP(int[][] dungeon) {
        int n = dungeon.length;
        int m = dungeon[0].length;
        List<List<List<int[]>>> memtable = new ArrayList<>(m);
        for (int i = 0; i < n; i++) {
            ArrayList<List<int[]>> row = new ArrayList<>(n);
            for (int j = 0; j < m; j++) {
                row.add(null);
            }
            memtable.add(row);
        }
        return solve(dungeon, memtable, n - 1, m - 1).stream().min(Comparator.comparingInt(arr -> arr[0])).orElseThrow()[0];
    }

    private List<int[]> solve(int[][] dungeon, List<List<List<int[]>>> memtable, int y, int x) {
        List<int[]> res = memtable.get(y).get(x);
        if (res != null)
            return res;
        int roomValue = dungeon[y][x];
        if (y == 0 && x == 0) {
            res = Collections.singletonList(
                new int[] {
                    roomValue > 0 ? 1 : -roomValue + 1,
                    roomValue > 0 ? 1 + roomValue : 1
                }
            );
        } else {
            List<int[]> fromLeft = x > 0 ? solve(dungeon, memtable, y, x - 1) : null;
            List<int[]> fromTop = y > 0 ? solve(dungeon, memtable, y - 1, x) : null;
            res = merge(fromLeft, fromTop);
            for (int[] somePath : res) {
                int hpToReach = somePath[0];
                int remainingHp = somePath[1];
                somePath[0] = roomValue >= 0 || remainingHp > -roomValue ? hpToReach : hpToReach + 1 + -(remainingHp - -roomValue);
                somePath[1] = roomValue >= 0 ? remainingHp + roomValue : Math.max(1, remainingHp - -roomValue);
            }
        }
        memtable.get(y).set(x, res);
        return res;
    }

    private List<int[]> merge(List<int[]> fromLeft, List<int[]> fromTop) {
        if (fromLeft == null)
            return Collections.singletonList(new int[] {fromTop.get(0)[0], fromTop.get(0)[1]});
        else if (fromTop == null)
            return Collections.singletonList(new int[] {fromLeft.get(0)[0], fromLeft.get(0)[1]});
        else {
            List<int[]> temp = new ArrayList<>(1);
            temp.addAll(fromLeft);
            temp.addAll(fromTop);
            List<int[]> res = new ArrayList<>(1);
            outer: for (int i = 0; i < temp.size(); i++) {
                int[] candidate = temp.get(i);
                boolean passed = true;
                for (int j = 0; j < temp.size(); j++) {
                    if (j == i) {
                        continue;
                    }
                    int[] other = temp.get(j);
                    if (candidate[0] > other[0] && candidate[1] <= other[1])
                        passed = false;
                    else if (candidate[0] == other[0] && candidate[1] < other[1])
                        passed = false;
                    else if (candidate[0] >= other[0] && candidate[1] <= other[1]) {
                        passed = i < j;
                    }
                    if (!passed)
                        continue outer;
                }
                res.add(new int[] {candidate[0], candidate[1]});
            }
            return res;
        }
    }

}