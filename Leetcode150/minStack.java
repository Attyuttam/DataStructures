//Min Stack: https://leetcode.com/problems/min-stack/description/?envType=study-plan-v2&envId=top-interview-150

//Pretty interesting problem, uses Linked list and keeps the latest min at the top always

class Node {
    public int val;
    public int lastMin;
    public Node next;

    Node(int val, int lastMin){
        this.val = val;
        this.lastMin = lastMin;
        this.next = null;
    }
}
class MinStack {
    Node top;
    public MinStack() {
        top = null;
    }
    
    public void push(int val) {
        if(top == null){
            top = new Node(val,val);
        }else{
            Node newNode = new Node(val,Math.min(top.lastMin,val));
            newNode.next = top;
            top = newNode;
        }
    }
    
    public void pop() {
        top = top.next;
    }
    
    public int top() {
        if(top==null)return -1;
        return top.val;
    }
    
    public int getMin() {
        if(top == null)return -1;
        return top.lastMin;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */