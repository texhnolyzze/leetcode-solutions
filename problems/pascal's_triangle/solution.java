class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        if (numRows == 0)
            return result;
        result.add(Arrays.asList(1));
        if (numRows == 1)
            return result;
        result.add(Arrays.asList(1, 1));
        if (numRows == 2)
            return result;
        for (int i = 2; i < numRows; i++) {
            List<Integer> prevRow = result.get(i - 1);
            List<Integer> row = new ArrayList<>();
            row.add(1);
            for (int j = 0; j < i - 1; j++) {
                row.add(prevRow.get(j) + prevRow.get(j + 1));
            }
            row.add(1);
            result.add(row);
        }
        return result;
    }
}