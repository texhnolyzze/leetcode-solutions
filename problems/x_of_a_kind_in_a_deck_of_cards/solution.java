class Solution {
    public boolean hasGroupsSizeX(int[] deck) {
        int[] countOf = new int[10000];
        int maxCountOf = 0;
        for (int i = 0; i < deck.length; i++) {
            countOf[deck[i]]++;
            maxCountOf = Math.max(maxCountOf, countOf[deck[i]]);
        }
        int gcd = maxCountOf;
        for (int i = 0; i < countOf.length; i++) {
            if (countOf[i] != 0) {
                gcd = gcd(gcd, countOf[i]);
                if (gcd < 2)
                    return false;
            }
        }
        return true;
    }
    
    private int gcd(int a, int b) {
        int temp;
        while (b != 0) {
            a %= b;
            temp = a;
            a = b;
            b = temp;
        }
        return a;
    }
}