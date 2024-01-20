//Linked list cycle: https://leetcode.com/problems/linked-list-cycle/description/?envType=study-plan-v2&envId=top-interview-150

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode sp = head;
        ListNode fp = head;

        while(fp!=null){
            sp = sp.next;
            fp = fp.next;
            if(fp!=null)fp = fp.next;

            if(sp!=null && sp == fp)return true;
        }
        return false;
    }
}