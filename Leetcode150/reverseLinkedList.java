//Reverse linked list: https://leetcode.com/problems/reverse-linked-list-ii/description/?envType=study-plan-v2&envId=top-interview-150

/*
Reverse linked list is just a play with pointers and really does not test your logical thinking prowess
*/

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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode p = head;
        ListNode sp = null;

        int origLeft = left;

        while(p!=null && left-1 > 0){
            sp = p;
            p = p.next;
            left--;
        }

        ListNode c = p.next;
        ListNode n = c!=null ? c.next : null;

        int val = origLeft;
        while(c!=null && val<right){
            val++;
            c.next = p;
            p = c;
            c = n;
            if(n!=null)n = n.next;
        }
        if(origLeft == 1){
            head.next = c;
            return p;
        }
        if(sp!=null && sp.next!=null){
            sp.next.next = c;
            sp.next = p;
        }
        return head;
    }
}