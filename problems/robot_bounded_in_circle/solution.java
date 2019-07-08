class Solution {
    public boolean isRobotBounded(String instructions) {
        final int north = 0, west = 1, south = 2, east = 3;
        int x = 0, y = 0;
        int dir = north;
        for (int n = 0; n < 4; n++) {
            for (int i = 0; i < instructions.length(); i++) {
                char c = instructions.charAt(i);
                switch (dir) {
                    case north:
                        switch (c) {case 'G': y++; break; case 'L': dir = west; break; case 'R': dir = east; break;}
                        break;
                    case west:
                        switch (c) {case 'G': x--; break; case 'L': dir = south; break; case 'R': dir = north; break;}
                        break;
                    case south:
                        switch (c) {case 'G': y--; break; case 'L': dir = east; break; case 'R': dir = west; break;}
                        break;
                    case east:
                        switch (c) {case 'G': x++; break; case 'L': dir = north; break; case 'R': dir = south; break;}
                        break;
                }
            }
        }
        return x == 0 && y == 0;
    }
}