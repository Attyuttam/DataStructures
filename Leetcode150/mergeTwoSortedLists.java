//Merge two sorted lists: https://leetcode.com/problems/merge-two-sorted-lists/description/?envType=study-plan-v2&envId=top-interview-150

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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode p1 = list1;
        ListNode p2 = list2;

        ListNode head = null;
        ListNode tail = null;
        
        int val = 0;
        while(p1!=null && p2!=null){
            if(p1.val <= p2.val){
                val = p1.val;
                p1 = p1.next;
            }else{
                val = p2.val;
                p2 = p2.next;
            }
            if(head == null){
                head = new ListNode(val);
                tail = head;
            }else{
                tail.next = new ListNode(val);
                tail = tail.next;
            }
        }
        while(p1!=null){
            if(head == null){
                head = new ListNode(p1.val);
                tail = head;
            }else{
                tail.next = new ListNode(p1.val);
                tail = tail.next;
            }
            p1 = p1.next;
        }
        while(p2!=null){
            if(head == null){
                head = new ListNode(p2.val);
                tail = head;
            }else{
                tail.next = new ListNode(p2.val);
                tail = tail.next;
            }
            p2 = p2.next;
        }
        return head;
    }
}