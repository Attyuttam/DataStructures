//merge k sorted trees: 

/*
My solution that beats 70%.

Apparently the merge sort approach is the fastest
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
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0)return null;
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a,b)->Integer.compare(a.val,b.val));

        for(int i=0;i<lists.length;i++){
            if(lists[i]!=null)
                pq.add(lists[i]);
        }

        ListNode head = new ListNode(0);
        ListNode ptr = head;
        
        while(!pq.isEmpty()){
            ListNode n = pq.poll();
            ptr.next = n;
            ptr = ptr.next;

            if(ptr.next != null)
                pq.add(ptr.next);
        }

        ptr.next = null;
        return head.next;
    }
}