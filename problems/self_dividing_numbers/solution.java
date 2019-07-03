class Solution {
    public static List<Integer> selfDividingNumbers(int left, int right) {
        return IntStream.rangeClosed(left, right).filter(n -> 
            Integer.toString(n).chars().allMatch(digit -> digit - 48 != 0 && n % (digit - 48) == 0)
        ).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);           
    }
}