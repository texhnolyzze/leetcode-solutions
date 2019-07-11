class Solution {
    public static String[] findRestaurant(String[] list1, String[] list2) {
        if (list1.length > list2.length) {
            String[] temp = list1;
            list1 = list2;
            list2 = temp;
        }
        Map<String, Integer> indexOf = new HashMap<>();
        for (int i = 0; i < list1.length; i++)
            indexOf.put(list1[i], i);
        int countPairsWithMinIndexSum = 1; // answer always exists.
        int minIndexSum = list1.length + list2.length;
        for (int i = 0; i < list2.length; i++) {
            String s = list2[i];
            Integer idx = indexOf.get(s);
            if (idx != null) {
                int sum = i + idx;
                if (minIndexSum > sum) {
                    minIndexSum = sum;
                    countPairsWithMinIndexSum = 1;
                } else if (minIndexSum == sum) 
                    countPairsWithMinIndexSum++;
            }
        }
        String[] res = new String[countPairsWithMinIndexSum];
        for (int i = 0, j = 0; i < list2.length; i++) {
            String s = list2[i];
            Integer idx = indexOf.get(s);
            if (idx != null) {
                if (idx + i == minIndexSum) 
                    res[j++] = s;
            }
        }
        return res;
    }
}