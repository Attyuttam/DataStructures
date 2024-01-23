//Remove nth node from the linked list: https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/?envType=study-plan-v2&envId=top-interview-150

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
    public ListNode removeNthFromEnd(ListNode head, int k) {
        ListNode p = null;
        ListNode c = head;
        ListNode n = head;

        while(k-1>0 && n!=null){
            n = n.next;
            k--;
        }
        while(n.next!=null){
            p = c;
            c = c.next;
            n = n.next;
        }
        if(p==null){
            //The first node itself has to be removed
            return head.next;
        }
        if(c==n){
            //The last node has to be removed
            p.next = null;
        }else{
            p.next = c.next;
        }
        return head;
    }
}