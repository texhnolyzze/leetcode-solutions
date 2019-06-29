/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode n1, ListNode n2) {
        if (n1 == null)
            return n2;
        if (n2 == null)
            return n1;
        ListNode head;
        if (n1.val < n2.val) {
            head = new ListNode(n1.val);
            n1 = n1.next;
        } else {
            head = new ListNode(n2.val);
            n2 = n2.next;
        }
        ListNode res = head;
        while (n1 != null && n2 != null) {
            if (n1.val < n2.val) {
                res.next = new ListNode(n1.val);
                n1 = n1.next;
            } else {
                res.next = new ListNode(n2.val);
                n2 = n2.next;
            }
            res = res.next;
        }
        if (n1 != null) do {
            res.next = new ListNode(n1.val);
            res = res.next; 
            n1 = n1.next;
        } while (n1 != null);
        else if (n2 != null) do {
            res.next = new ListNode(n2.val);
            res = res.next; 
            n2 = n2.next;
        } while (n2 != null);
        return head;
    }
}