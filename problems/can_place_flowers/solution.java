class Solution {
    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        int numPlants = 0;
        int numPlanted = 0;
        int leftFlowerIndex = -2, rightFlowerIndex = 0;
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 1) {
                rightFlowerIndex = i;
                int len = rightFlowerIndex - leftFlowerIndex - 1;
                numPlanted += getNumCanBePlantedOnFlowerbedOf(len);
                leftFlowerIndex = rightFlowerIndex;
                numPlants++;
            }
        }
        if (numPlants == 0) 
            return getNumCanBePlantedOnFlowerbedOf(flowerbed.length + 2) >= n;
        else 
            numPlanted += getNumCanBePlantedOnFlowerbedOf(flowerbed.length - rightFlowerIndex);
        return numPlanted >= n;
    }
    
    private static int getNumCanBePlantedOnFlowerbedOf(int len) {
        return (len / 2 - (len % 2 + 1) % 2);
    }
}