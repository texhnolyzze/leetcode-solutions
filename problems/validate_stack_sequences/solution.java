class Solution {
    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        Set<Integer> alreadyPoppedIndex = new HashSet<>();
        Map<Integer, Integer> indexOf = new HashMap<>();
        for (int i = 0; i < pushed.length; i++)
            indexOf.put(pushed[i], i);
        int currPoppedIndex = 0;
        for (int i = 0; i < popped.length; i++) {
            int nextPopped = popped[i];
            int nextPoppedIndex = indexOf.get(nextPopped);
            alreadyPoppedIndex.add(nextPoppedIndex);
            int p = currPoppedIndex - 1;
            while (alreadyPoppedIndex.contains(p))
                p--;
            if (nextPoppedIndex < p) {
                return false;
            } else {
                if (nextPoppedIndex == popped.length - 1) {
                    currPoppedIndex = popped.length - 1;
                    for (int j = i + 1; j < popped.length; j++) {
                        nextPoppedIndex = indexOf.get(popped[j]);
                        if (nextPoppedIndex > currPoppedIndex)
                            return false;
                        currPoppedIndex = nextPoppedIndex;
                    }
                    return true;
                } else 
                    currPoppedIndex = nextPoppedIndex;
            }
        }
        return true;
    }
}