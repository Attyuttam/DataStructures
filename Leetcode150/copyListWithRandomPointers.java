//copy list with random pointers: https://leetcode.com/problems/copy-list-with-random-pointer/description/?envType=study-plan-v2&envId=top-interview-150

//This is the code that I came up with and could beat only 14.3% users
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        HashMap<Node, Node> hm = new HashMap<>();
        Node ptr = head;

        Node newHead = null;
        Node newTail = null;

        while(ptr!=null){
            if(newHead == null){
                newHead = new Node(ptr.val);
                newTail = newHead;
                hm.put(ptr,newHead);
            }else{
                newTail.next = new Node(ptr.val);
                newTail = newTail.next;
                hm.put(ptr,newTail);
            }
            ptr = ptr.next;
        }
        for(Map.Entry<Node,Node> e : hm.entrySet()){
            Node sourcePtr = e.getValue();
            Node targetRandomPointer = hm.get(e.getKey().random);

            sourcePtr.random = targetRandomPointer;
        }
        return newHead;
    }
}

//Then I saw a small optimisation and this optimisation beat 100%
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        Map<Node,Node> hm = new HashMap<>();
        Node ptr = head;

        while(ptr!=null){
            hm.put(ptr, new Node(ptr.val));
            ptr = ptr.next;
        }
        ptr = head;
        while(ptr!=null){
            hm.get(ptr).next = hm.get(ptr.next);
            hm.get(ptr).random = hm.get(ptr.random);
            ptr = ptr.next;
        }
        return hm.get(head);
    }
}
