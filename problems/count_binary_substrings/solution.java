class Solution {
    public int countBinarySubstrings(String s) {
        int ans = 0;
        int zeroCounter;
        int oneCounter;
        if (s.charAt(0) == '0') {
            zeroCounter = 1;
            oneCounter = 0;
        } else {
            zeroCounter = 0;
            oneCounter = 1;
        }
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                if (s.charAt(i - 1) == '1')
                    zeroCounter = 0;
                zeroCounter++;
                if (oneCounter > 0) {
                    ans++;
                    oneCounter--;
                }
            } else {
                if (s.charAt(i - 1) == '0')
                    oneCounter = 0;
                oneCounter++;
                if (zeroCounter > 0) {
                    ans++;
                    zeroCounter--;
                }
            }
        }
        return ans;
    }
}