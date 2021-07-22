class Solution {
    public List<Integer> numOfBurgers(
        final int tomatoSlices,
        final int cheeseSlices
    ) {
        if (tomatoSlices % 2 != 0) {
            return Collections.emptyList();
        }
        final int tomatoesPerSmall = 2;
        final int tomatoesPerJumbo = 4;
        for (int numSmall = 0; numSmall <= cheeseSlices; numSmall++) {
            final int numJumbos = cheeseSlices - numSmall;
            if (tomatoSlices - (numSmall * tomatoesPerSmall + numJumbos * tomatoesPerJumbo) == 0) {
                return List.of(numJumbos, numSmall);
            }
        }
        return Collections.emptyList();
    }
}