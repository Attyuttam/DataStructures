//Rotate list: https://leetcode.com/problems/rotate-list/description/?envType=study-plan-v2&envId=top-interview-150

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
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null)return head;

        int numEl = 0;
        ListNode ptr = head;
        
        while(ptr!=null){
            numEl++;
            ptr = ptr.next;
        }

        k = k>numEl ? k%numEl : k;

        if(k == numEl || k==0)return head;

        int i = 0;
        ptr = head;
        ListNode prev = head;
        while(i < numEl - k){
            prev = ptr;
            ptr = ptr.next;
            i++;
        }

        prev.next = null;

        ListNode newHead = ptr;
        
        while(ptr.next!=null){
            ptr = ptr.next;
        }
        ptr.next = head;
        return newHead;
    }
}