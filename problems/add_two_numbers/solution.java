/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode prev = null;
        ListNode result = null;
        boolean carry = false;
        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val;
            if (carry) {
                sum++;
                if (sum > 9) {
                    result = append(sum - 10, prev);
                } else {
                    result = append(sum, prev);
                    carry = false;
                }
            } else {
                if (sum > 9) {
                    carry = true;
                    result = append(sum - 10, prev);
                } else 
                    result = append(sum, prev);;
            }
            result.next = prev;
            prev = result;
            l1 = l1.next;
            l2 = l2.next;
        }
        ListNode rest = l1 != null ? l1 : l2 != null ? l2 : null;
        if (rest != null) {
            do {
                if (carry) {
                    if (rest.val + 1 < 10) {
                        carry = false;
                        result = append(rest.val + 1, prev);
                    } else
                        result = append(0, prev);
                } else
                    result = append(rest.val, prev);
                prev = result;
                rest = rest.next;
            } while (rest != null);
        }
        if (carry) 
            result = append(1, prev);
        return reverse(result);
    }
    
    private static ListNode append(int val, ListNode prev) {
        ListNode n = new ListNode(val);
        n.next = prev;
        return n;
    }
    
    private static ListNode reverse(ListNode n) {
        ListNode curr = n;
        ListNode next = n.next;
        curr.next = null;
        while (next != null) {
            ListNode temp = next.next;
            next.next = curr;
            curr = next;
            next = temp;
        }
        return curr;
    }
}