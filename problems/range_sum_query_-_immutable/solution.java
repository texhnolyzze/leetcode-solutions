import java.util.SortedMap;
import java.util.TreeMap;

class NumArray {

    private final SortedMap<Integer, Integer> tree = new TreeMap<>();
        
    public NumArray(int[] nums) {
        for (int i = 0; i < nums.length; i++) 
            tree.put(i, nums[i]);
    }

    public int sumRange(int i, int j) {
            SortedMap<Integer, Integer> subTree = tree.subMap(i, j + 1);
            int sum = 0;
            for (int n : subTree.values())
                sum += n;
            return sum;
        }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */