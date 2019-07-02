class Solution {
    public List<Integer> getRow(int rowIndex) {
        if (rowIndex == 0)
            return Arrays.asList(1);
        if (rowIndex == 1)
            return Arrays.asList(1, 1);
        List<Integer> prevRow = new LinkedList(Arrays.asList(1, 1));
        List<Integer> row = new LinkedList<>(Arrays.asList(1, null, 1));
        for (int i = 2;;i++) {
            ListIterator<Integer> rowIterator = row.listIterator();
            ListIterator<Integer> prevRowIterator = prevRow.listIterator();
            rowIterator.next(); // skip first "1"
            int j = prevRowIterator.next(), k;
            while (prevRowIterator.hasNext()) {
                rowIterator.next();
                k = prevRowIterator.next();
                Integer sum = j + k;
                rowIterator.set(sum);
                prevRowIterator.set(sum);
                j = k;
            }
            if (i == rowIndex)
                return row;
            row.add(1);
            prevRow.add(1);
        }
    }
}