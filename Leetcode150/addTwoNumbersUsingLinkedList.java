//Add two numbers given in the form of a linked list: https://leetcode.com/problems/add-two-numbers/description/?envType=study-plan-v2&envId=top-interview-150

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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int c = 0;
        ListNode pl1 = l1;
        ListNode pl2 = l2;

        ListNode head = null;
        ListNode tail = null;

        while(pl1!=null && pl2!=null){
            int val = pl1.val + pl2.val + c;
            c = 0;
            if(val > 9){
                c = val/10;
                val%=10;
            }

            if(head == null){
                head = new ListNode(val);
                tail = head;
            }else{
                tail.next = new ListNode(val);
                tail = tail.next;
            }

            pl1 = pl1.next;
            pl2 = pl2.next;
        }

        while(pl1!=null){
            int val = pl1.val + c;
            c = 0;
            if(val > 9){
                c = val/10;
                val%=10;
            }

            if(head == null){
                head = new ListNode(val);
                tail = head;
            }else{
                tail.next = new ListNode(val);
                tail = tail.next;
            }

            pl1 = pl1.next;
        }
        while(pl2!=null){
            int val = pl2.val + c;
            c = 0;
            if(val > 9){
                c = val/10;
                val%=10;
            }

            if(head == null){
                head = new ListNode(val);
                tail = head;
            }else{
                tail.next = new ListNode(val);
                tail = tail.next;
            }

            pl2 = pl2.next;
        }
        if(c!=0){
            tail.next = new ListNode(c);
            tail = tail.next;
        }
        return head;
    }
}