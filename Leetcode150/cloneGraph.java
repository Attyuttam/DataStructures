//clone graph: https://leetcode.com/problems/clone-graph/description/?envType=study-plan-v2&envId=top-interview-150

/*
Beats 100% users
*/

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    Map<Integer,Node> hm = new HashMap<>();
    int[] ncheck = new int[101];

    public Node cloneGraph(Node node) {
        if(node == null) return node;

        Node n = new Node(node.val);
        hm.put(n.val, n);
        ncheck[n.val] = 1;

        for(int i=0;i<node.neighbors.size();i++){
            Node neighbor = node.neighbors.get(i);
            if(ncheck[neighbor.val] == 1){
                n.neighbors.add(hm.get(neighbor.val));
            }else{
                Node c = cloneGraph(neighbor);
                hm.put(c.val, c);
                n.neighbors.add(c);
            }
        }
        return n;
    }
}