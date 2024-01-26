//Remove duplicates from a sorted list: https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/description/?envType=study-plan-v2&envId=top-interview-150

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
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null)return head;

        ListNode p = null;
        ListNode c = head;
        ListNode n = head.next;
        while(n!=null){
            while(c!=null && n!=null && c.val!=n.val){
                p = c;
                c = c.next;
                n = n.next;
            }
            while(c!=null && n!=null && c.val == n.val) n = n.next;

            if(c.next != n && c!=head){
                c = n;
                p.next = c;
                if(n != null)n = n.next;
            }else if(c.next != n){
                c = n;
                head = c;
                if(n != null)n = n.next;
            }
        }
        return head;
    }
}