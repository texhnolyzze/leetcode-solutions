class Solution {
    public int countSegments(String s) {
        int count = 0;
        boolean segment = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                if (segment)
                    count++;
                segment = false;
            } else {
                if (!segment)
                    segment = true;
            }
        }
        return segment ? count + 1 : count;
    }
}