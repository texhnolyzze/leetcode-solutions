class Solution {
    public String addStrings(String num1, String num2) {
        if (num1.length() > num2.length()) {
            String temp = num1;
            num1 = num2;
            num2 = temp;
        }
        boolean carry = false;
        StringBuilder res = new StringBuilder();
        int i = num1.length() - 1, j = num2.length() - 1;
        for (; i >= 0; i--, j--) {
            int n1dgt = num1.charAt(i) - '0';
            int n2dgt = num2.charAt(j) - '0';
            int sum = n1dgt + n2dgt;
            if (carry) {
                sum++;
                if (sum > 9) 
                    res.append(sum - 10);
                else {
                    carry = false;
                    res.append(sum);
                }
            } else {
                if (sum > 9) {
                    carry = true;
                    res.append(sum - 10);
                } else 
                    res.append(sum);
            }
        }
        while (j >= 0) {
            int n2dgt = num2.charAt(j--) - '0';
            if (carry) {
                n2dgt++;
                if (n2dgt < 10) {
                    carry = false;
                    res.append(n2dgt);
                } else res.append(0);
            } else
                res.append(n2dgt);
        }
        if (carry)
            res.append(1);
        return res.reverse().toString();
    }
}