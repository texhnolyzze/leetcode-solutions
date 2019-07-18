class Solution {
    public static String convert(String s, int numRows) {
        if (numRows == 1)
            return s;
        StringBuilder sb = new StringBuilder();
        int[][] delta = new int[numRows][2];
        delta[0][0] = delta[0][1] = (numRows - 1) * 2 - 1; 
        for (int i = 1, dt = 1; i < numRows / 2; i++, dt += 2) {
            delta[i][0] = delta[i - 1][0] - 2;
            delta[i][1] = dt;
        }
        delta[numRows - 1][0] = delta[numRows - 1][1] = (numRows - 1) * 2 - 1;
        for (int i = numRows - 2, dt = 1; i >= numRows / 2; i--, dt += 2) {
            delta[i][0] = dt;
            delta[i][1] = delta[i + 1][1] - 2;
        }
        for (int i = 0; i < numRows; i++) {
            int toggle = 0;
            for (int j = i; j < s.length();) {
                sb.append(s.charAt(j));
                j += delta[i][toggle] + 1;
                toggle = (toggle + 1) % 2;
            }
        }
        return sb.toString();
    }
}