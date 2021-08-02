class Solution {
    public int bitwiseComplement(final int n) {
        if (n == 0) {
            return 1;
        }
        int complement = 0;
        for (int i = 31; i >= 0; i--) {
            if ((n & (1 << i)) != 0) {
                for (int j = i - 1; j >= 0; j--) {
                    if ((n & (1 << j)) == 0) {
                        complement |= (1 << j);
                    }
                }
                break;
            }
        }
        return complement;
    }
}