//Sort a Linked list: 

/*
O(n) space and O(nlogn) time complexity

but it still beats 27% users only

- because my merging logic is very slow

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
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null)return head;

        ListNode temp = getMid(head);
        ListNode head2 = temp.next;
        temp.next = null;

        
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(head2);

        //System.out.println(l1.val+" "+l2.val);
        return merge(l1,l2);
    }
    private ListNode getMid(ListNode head){
        ListNode s = head;
        ListNode f = head;

        while(f.next!=null && f.next.next!=null){
            s = s.next;
            f = f.next.next;
        }
        return s;
    }
    private ListNode merge(ListNode p1, ListNode p2){
        ListNode m = null;
        ListNode head = null;

        while(p1!=null && p2!=null){
            if(head == null){
                if(p1.val <= p2.val){
                    head = new ListNode(p1.val);
                    m = head;
                    p1 = p1.next;
                }else{
                    head = new ListNode(p2.val);
                    m = head;
                    p2 = p2.next;
                }
            }else{
                if(p1.val <= p2.val){
                    m.next = new ListNode(p1.val);
                    m = m.next;
                    p1 = p1.next;
                }else{
                    m.next = new ListNode(p2.val);
                    m = m.next;
                    p2 = p2.next;
                }
            }
        }
        while(p1!=null){
            if(head == null){
                head = new ListNode(p1.val);
                m = head;
            }else{
                m.next = new ListNode(p1.val);
                m = m.next;
            }
            p1 = p1.next;
        }
        while(p2!=null){
            if(head == null){
                head = new ListNode(p2.val);
                m = head;
            }else{
                m.next = new ListNode(p2.val);
                m = m.next;
            }
            p2 = p2.next;
        }
        return head;
    }
}

/*
Fixed merge
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
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null)return head;

        ListNode temp = getMid(head);
        ListNode head2 = temp.next;
        temp.next = null;

        
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(head2);

        //System.out.println(l1.val+" "+l2.val);
        return merge(l1,l2);
    }
    private ListNode getMid(ListNode head){
        ListNode s = head;
        ListNode f = head;

        while(f.next!=null && f.next.next!=null){
            s = s.next;
            f = f.next.next;
        }
        return s;
    }
    private ListNode merge(ListNode p1, ListNode p2){
        ListNode head;

        ListNode temp = new ListNode(0);
        head = temp;

        while(p1!=null && p2!=null){
            if(p1.val <= p2.val){
                temp.next = p1;
                p1 = p1.next;
                temp = temp.next;
            }else{
                temp.next = p2;
                p2 = p2.next;
                temp = temp.next;
            }
        }
        if(p1 == null){
            temp.next = p2;
        }
        if(p2 == null){
            temp.next = p1;
        }
        return head.next;
    }
}