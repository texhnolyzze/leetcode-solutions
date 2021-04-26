/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public static class Pair {
        final ListNode left;
        final ListNode right;
        public Pair(ListNode left, ListNode right) {
            this.left = left;
            this.right = right;
        }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1 || head == null)
            return head;
        ListNode newHead = null;
        ListNode temp1 = head;
        ListNode temp2 = null;
        while (true) {
            Pair pair = reverseGroup(temp1, k);
            if (pair == null) {
                if (newHead != null) {
                    temp2.next = temp1;
                    return newHead;
                } else {
                    return head;
                }
            }
            if (temp2 != null)
                temp2.next = pair.left;
            if (newHead == null)
                newHead = pair.left;
            temp2 = temp1;
            temp1 = pair.right;
            if (temp1 == null) {
                temp2.next = null;
                return newHead;
            }
        }
    }

    private Pair reverseGroup(ListNode head, int k) {
        if (notEnoughInGroup(head, k))
            return null;
        int count = 1;
        ListNode prev = head;
        ListNode curr = head.next;
        while (count != k) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
            count++;
        }
        return new Pair(prev, curr);
    }

    private boolean notEnoughInGroup(ListNode head, int k) {
        int count = 1;
        ListNode curr = head;
        while (curr.next != null && count < k) {
            count++;
            curr = curr.next;
        }
        return count != k;
    }
}