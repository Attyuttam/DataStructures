//LRU cache: https://leetcode.com/problems/lru-cache/description/?envType=study-plan-v2&envId=top-interview-150

class DNode {
    public DNode next;
    public DNode prev;
    public int key;
    public int val;

    DNode(int key, int val){
        this.key = key;
        this.val = val;
        this.next = null;
        this.prev = null;
    }
}
class LRUCache {
    DNode head;
    DNode tail;
    int capacity;
    int numEl;
    Map<Integer, DNode> hm;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.head = null;
        this.tail = null;
        this.hm = new HashMap<>();
        this.numEl = 0;
    }
    private void remove(DNode n){
        if(n == this.head){
            if(this.head.next == null){
                this.head = null;
                this.tail = null;
            }else{
                this.head = this.head.next;
                this.head.prev = null;
            }
        }else if(n == this.tail){
            this.tail = this.tail.prev;
            this.tail.next = null;
        }else{
            DNode prevEl = n.prev;
            DNode nextEl = n.next;

            prevEl.next = nextEl;
            nextEl.prev = prevEl;
        }
    }
    private void insert(DNode n){
        if(this.head == null){
            this.head = n;
            this.tail = n;
        }else{
            n.prev = this.tail;
            this.tail.next = n;
            this.tail = n;
        }
    }
    
    public int get(int key) {
        DNode n = hm.getOrDefault(key, new DNode(-1,-1));
        if(n.val == -1)return -1;

        remove(n);
        insert(n);

        return n.val;
    }
    
    public void put(int key, int value) {
        if(hm.getOrDefault(key,new DNode(-1,-1)).val != -1){
            DNode p = hm.get(key);
            p.val = value;
            hm.put(key,p);
            remove(p);
            insert(p);
        }else{
            if(this.numEl == this.capacity){
                //remove the LRU element
                DNode p = this.head;
                hm.remove(p.key);
                remove(p);
                this.numEl--;
            }
            DNode newVal = new DNode(key,value);
            hm.put(key,newVal);
            insert(newVal);
            this.numEl++;
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */