/*
// Employee info
class Employee {
    // It's the unique id of each node;
    // unique id of this employee
    public int id;
    // the importance value of this employee
    public int importance;
    // the id of direct subordinates
    public List<Integer> subordinates;
};
*/
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.function.Function;
import static java.util.stream.Collectors.toMap;

class Solution {
    public int getImportance(List<Employee> employees, int id) {
        Map<Integer, Employee> map = employees.stream().collect(toMap(e -> e.id, Function.identity()));
        Queue<Integer> q = new LinkedList<>();
        q.add(id);
        int importance = 0;
        while (!q.isEmpty()) {
            int subId = q.poll();
            Employee e = map.get(subId);
            importance += e.importance;
            q.addAll(e.subordinates);
        }
        return importance;
    }
}