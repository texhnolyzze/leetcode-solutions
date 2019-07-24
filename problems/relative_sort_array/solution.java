class Solution {
    public static int[] relativeSortArray(int[] arr1, int[] arr2) {
        Map<Integer, Integer> indexOf = new HashMap<>();
        for (int i = 0; i < arr2.length; i++)
            indexOf.put(arr2[i], i);
        Integer[] arr1Boxed = new Integer[arr1.length];
        for (int i = 0; i < arr1.length; i++)
            arr1Boxed[i] = arr1[i];
        Comparator<Integer> cmp = (n1, n2) -> {
            Integer n1Index = indexOf.get(n1);
            if (n1Index == null) {
                Integer n2Index = indexOf.get(n2);
                if (n2Index == null)
                    return Integer.compare(n1, n2);
                return 1;
            } else {
                Integer n2Index = indexOf.get(n2);
                if (n2Index == null)
                    return -1;
                return Integer.compare(n1Index, n2Index);
            }
        };
        Arrays.sort(arr1Boxed, cmp);
        for (int i = 0; i < arr1Boxed.length; i++) 
            arr1[i] = arr1Boxed[i];
        return arr1;
    }
}