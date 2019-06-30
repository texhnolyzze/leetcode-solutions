/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode temp = head;
        ListNode headCpy = head;
        while (temp != null) {
            int val = temp.val;
            while (temp.next != null && temp.next.val == val)
                temp.next = temp.next.next;
            temp = temp.next;
        }
        return headCpy;
    }
}