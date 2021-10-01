class Solution {
    public int minPartitions(String n) {
        return n.chars().max().stream().map(dig -> dig - '0').findAny().orElseThrow();
    }
}