/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int size = size(head);
        if (size - n == 0)
            return head.next;
        ListNode curr = head;
        ListNode prev = null;
        for (int i = 0; i < size - n; i++) {
            prev = curr;
            curr = curr.next;
        }
            prev.next = curr.next;
        return head;
    }
    
    private static int size(ListNode head) {
        int size = 0;
        while (head != null) {
            head = head.next;
            size++;
        }
        return size;
    }
}