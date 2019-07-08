import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

class Solution {
    public int findLHS(int[] nums) {
        Map<Integer, Long> counted = Arrays.stream(nums).boxed().collect(groupingBy(identity(), counting()));
        long longest = 0;
        for (Map.Entry<Integer, Long> e : counted.entrySet()) {
            Long min = counted.get(e.getKey() - 1);
            if (min != null)
                longest = Math.max(longest, e.getValue() + min);
        }
        return (int) longest;
    }
}