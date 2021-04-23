import java.util.ArrayList;
import java.util.List;

public class Solution {

    public int minimumTotal(List<List<Integer>> triangle) {
        return min(triangle, triangle.size() - 1).stream().min(Integer::compareTo).orElseThrow();
    }

    private List<Integer> min(List<List<Integer>> triangle, int idx) {
        if (idx == 0)
            return triangle.get(0);
        List<Integer> res = new ArrayList<>(idx + 1);
        List<Integer> prevMin = min(triangle, idx - 1);
        List<Integer> currRow = triangle.get(idx);
        for (int i = 0; i < currRow.size(); i++) {
            Integer currWeight = currRow.get(i);
            int topMin;
            if (i == currRow.size() - 1) {
                topMin = prevMin.get(i - 1);
            } else if (i == 0) {
                topMin = prevMin.get(0);
            } else {
                topMin = Math.min(prevMin.get(i), prevMin.get(i - 1));
            }
            res.add(currWeight + topMin);
        }
        return res;
    }

}