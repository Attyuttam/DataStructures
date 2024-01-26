//Partition list: https://leetcode.com/problems/partition-list/description/?envType=study-plan-v2&envId=top-interview-150

//This solution beats only 13.77% users

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
    public ListNode partition(ListNode head, int x) {
        if(head ==null || head.next == null)return head;

        ListNode c = head;
        ListNode p = null;
        ListNode n = head.next;

        boolean ve = false;

        List<Integer> lessValues = new ArrayList<>();
        while(c!=null){
            if(c.val >= x){
                ve = true;
            }
            if(ve==true && c.val < x){
                //delete c
                lessValues.add(c.val);
                p.next = n;
            }else{
                p = c;
            }
            c = n;
            if(n!=null)n=n.next;
        }
        p = null;
        c = head;
        while(c!=null && c.val<x){
            p = c;
            c = c.next;
        }
        ListNode newHead = head;
        for(int i=0;i<lessValues.size();i++){
            if(p == null){
                p = new ListNode(lessValues.get(i));
                newHead = p;
            }else{
                p.next = new ListNode(lessValues.get(i));
                p = p.next;
            }
        }
        if(p != null)
            p.next = c;
        return newHead;
    }
}

/*
This beautiful solution takes into account that we can maintain two different lists that picks up the 
greater than or less than x values and finally combines them !

Original solution: https://leetcode.com/problems/partition-list/solutions/3915085/simple-solution-o-n-time

Beats 100% users
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
    public ListNode partition(ListNode head, int x) {
        ListNode l1 = new ListNode(0);
        ListNode l2 = new ListNode(0);

        ListNode p1 = l1;
        ListNode p2 = l2;

        ListNode p = head;
        
        while(p != null){
            if(p.val < x){
                p1.next = p;
                p = p.next;
                p1 = p1.next;
                p1.next = null;
            }else{
                p2.next = p;
                p = p.next;
                p2 = p2.next;
                p2.next = null;
            }
        }
        p1.next = l2.next;
        return l1.next;
    }
}