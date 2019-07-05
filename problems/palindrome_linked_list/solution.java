/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isPalindrome(ListNode head) {
        int size = size(head);
        if (size <= 1)
            return true;
        ListNode[] temp = reverse(head, size / 2);
        ListNode toLeft = temp[0];
        ListNode toRight = size % 2 == 0 ? temp[1] : temp[1].next;
        while (toLeft != null) {
            if (toLeft.val != toRight.val)
                return false;
            toLeft = toLeft.next;
            toRight = toRight.next;
        }
        return true;
    }
    
    private static ListNode[] reverse(ListNode head, int n) {
        int i = 0;
        ListNode curr = head;
        ListNode next = curr.next;
        curr.next = null;
        while (i < n - 1) {
            ListNode temp = next.next;
            next.next = curr;
            curr = next;
            next = temp;
            i++;
        }
        return new ListNode[] {curr, next};
    }
    
    private static int size(ListNode head) {
        int size = 0;
        while (head != null) {
            size++;
            head = head.next;
        }
        return size;
    }
}