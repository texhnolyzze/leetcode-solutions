import java.util.*;

public class Solution {

    public int maxEvents(int[][] events) {
        Comparator<int[]> comparator = Comparator.comparingInt((int[] event) -> event[0]).thenComparingInt((int[] event) -> event[1]).thenComparingInt(Objects::hashCode);
        TreeSet<int[]> eventSet = new TreeSet<>(comparator);
        int minDay = Integer.MAX_VALUE;
        int maxDay = -1;
        for (int[] event : events) {
            minDay = Math.min(minDay, event[0]);
            maxDay = Math.max(maxDay, event[1]);
            eventSet.add(event);
        }
        int attends = 0;
        for (int day = minDay; day <= maxDay; day++) {
            int[] candidateEvent = null;
            for (int[] event : eventSet) {
                if (event[0] > day) {
                    break;
                }
                if (
                    (day <= event[1] &&
                    (candidateEvent == null || candidateEvent[1] > event[1]))
                ) {
                    candidateEvent = event;
                    if (candidateEvent[1] == day)
                        break;
                }
            }
            if (candidateEvent != null) {
                attends++;
                eventSet.remove(candidateEvent);
            }
        }
        return attends;
    }

}