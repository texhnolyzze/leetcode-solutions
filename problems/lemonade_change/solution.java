class Solution {
    public boolean lemonadeChange(int[] bills) {
        int five$Bills = 0;
        int ten$Bills = 0;
        for (int i = 0; i < bills.length; i++) {
            switch (bills[i]) {
                case 5:
                    five$Bills++;
                    break;
                case 10:
                    if (five$Bills == 0)
                        return false;
                    five$Bills--;
                    ten$Bills++;
                    break;
                default:
                    if (ten$Bills != 0) {
                        ten$Bills--;
                        if (five$Bills != 0) 
                            five$Bills--;
                        else
                            return false;
                    } else {
                        if (five$Bills < 3)
                            return false;
                        five$Bills -= 3;
                    }
                    break;
            }
        }
        return true;
    }
}