class Solution {
    public static String addBinary(String a, String b) {
        if (a.length() < b.length()) {
            String temp = a;
            a = b;
            b = temp;
        }
        boolean carry = false;
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1, j = b.length() - 1;
        for (; j >= 0; i--, j--) {
            char ac = a.charAt(i);
            char bc = b.charAt(j);
            if (ac == bc) {
                if (ac == '1') { // bc == '1'
                    if (carry) {
                        sb.append(1);
                    } else {
                        sb.append(0);
                        carry = true;
                    }
                } else { // bc == '0'
                    if (carry) {
                        sb.append(1);
                        carry = false;
                    } else
                        sb.append(0);
                }
            } else {
                if (carry) 
                    sb.append(0);
                else 
                    sb.append(1);
            }
        }
        if (i >= 0 || carry) {
            while (i >= 0) {
                char ac = a.charAt(i--);
                if (ac == '1') {
                    if (carry)
                        sb.append(0);
                    else sb.append(1);
                } else {
                    if (carry) {
                        sb.append(1);
                        carry = false;
                    } else 
                        sb.append(0);
                }
            }
            if (carry)
                sb.append(1);
        } 
        return sb.reverse().toString();
    }
}