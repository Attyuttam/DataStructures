//Rever Nodes in k-group: https://leetcode.com/problems/reverse-nodes-in-k-group/description/?envType=study-plan-v2&envId=top-interview-150

/*
My solution that beats 36.5% users
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
     private ListNode reverseBetween(ListNode head, int left, int right) {
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
    private int getNumElements(ListNode head){
        int c = 0;
        while(head!=null){
            c++;
            head = head.next;
        }
        return c;
    }
    public ListNode reverseKGroup(ListNode head, int k) {
        int numEl = getNumElements(head);
        if(k > numEl){
            return head;
        }
        int l = 1;
        int r = k;
        while(r <= numEl){
            head = reverseBetween(head, l ,r);
            l+=k;
            r+=k;
        }
        return head;

    }
}


/*
Solution that beats 100% users, most ideal solution uses recursion, pretty interesting
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
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null)return null;
        
        ListNode curr = head;
        ListNode nxt = null;
        ListNode prev = null;
        
        int count = 0;
        while(curr!=null && count<k){
            curr = curr.next;
            count++;
        }
        if(count<k)return head;
        curr = head;
        count = 0;
        
        while(curr!=null && count<k){
            nxt = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nxt;
            count++;
        }
        
        if(nxt!=null){
            head.next = reverseKGroup(nxt,k);
        }
        
        return prev;
    }
}