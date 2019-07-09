class Solution {
    public static List<String> readBinaryWatch(int num) {
        List<String> res = new ArrayList<>();
        backtrack(0, num, 0, res);
        return new ArrayList<>(res);
    }
    
    private static void backtrack(int time, int numLEDOn, int fromIdx, List<String> res) {
        if (numLEDOn == 0) {
            String asString = timeAsString(time);
            if (asString != null)
                res.add(asString);
            return;
        }
        for (int i = fromIdx; i < 10; i++) {
            if (bitAt(i, time) == 0) 
                backtrack(setToOneAt(i, time), numLEDOn - 1, i + 1, res);   
        }
    }
    
    private static int bitAt(int idx, int n) {
        return (n >> idx) & 1; 
    }
    
    private static int setToOneAt(int idx, int n) {
        return n | (1 << idx);
    }
    
    private static String timeAsString(int time) {
        int min = time & (0b0000111111);
        int hour = (time & (0b1111000000)) >>> 6;
        if (min > 59 || hour > 11)
            return null;
        String res;
        if (hour == 0) {
            if (min < 10)
                res = "0:0" + Integer.toString(min);
            else
                res = "0:" + Integer.toString(min);
        } else {
            if (min < 10)
                res = Integer.toString(hour) + ":0" + Integer.toString(min);
            else
                res = Integer.toString(hour) + ":" + Integer.toString(min);
        }
        return res;
    }
}