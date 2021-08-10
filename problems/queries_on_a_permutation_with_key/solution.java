class Solution {
    public int[] processQueries(
        final int[] queries,
        final int m
    ) {
        ListNode head = new ListNode(1);
        ListNode curr = head;
        for (int i = 1; i < m; i++) {
            curr.next = new ListNode(i + 1);
            curr = curr.next;
        }
        final int[] result = new int[queries.length];
        ListNode prev;
        for (int i = 0; i < queries.length; i++) {
            final int query = queries[i];
            prev = null;
            curr = head;
            int j = 0;
            while (curr.value != query) {
                j++;
                prev = curr;
                curr = curr.next;
            }
            result[i] = j;
            if (prev != null) {
                prev.next = curr.next;
                curr.next = head;
                head = curr;
            }
        }
        return result;
    }

    private static class ListNode {

        ListNode next;
        final int value;

        private ListNode(
            final int value
        ) {
            this.value = value;
        }

    }
}