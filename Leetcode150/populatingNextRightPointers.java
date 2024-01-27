//populating next right pointers: https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/description/?envType=study-plan-v2&envId=top-interview-150

/*
	This solution could beat only 5.67% users.
	This solution generates a level order traversal and then makes them point to the next element
	in their level order traversal
	Not much of a cool solution 
*/
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    public Node connect(Node root) {
        if(root == null)return null;
        Queue<Node> q = new LinkedList<>();

        q.add(root);
        q.add(new Node(-1000));
        List<List<Node>> lot = new ArrayList<>();
        List<Node> temp = new ArrayList<>();
        while(!q.isEmpty()){
            Node popped = q.poll();
            
            if(popped.val == -1000){
                if(!q.isEmpty()){
                    q.add(new Node(-1000));
                }
                lot.add(new ArrayList<>(temp));
                temp.clear();
            }else{
                temp.add(popped);
                if(popped.left != null){
                    q.add(popped.left);
                }
                if(popped.right != null){
                    q.add(popped.right);
                }
            }
        }
        for(int i=0;i<lot.size();i++){
            for(int j=0;j<lot.get(i).size();j++){
                if(j == lot.get(i).size()-1){
                    lot.get(i).get(j).next = null;
                }else{
                    lot.get(i).get(j).next = lot.get(i).get(j+1);
                }
            }
        }
        return root;
    }
}
/*
A beautiful solution that beats 76% users
https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/solutions/3771036/java-the-simplest-solution-bfs

*/

/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        bfs(root);
        return root;
    }

    private void bfs(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();

            Node previousRight = null;
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                
                node.next = previousRight;
                previousRight = node;
                
                if (node.right != null) {
                    queue.add(node.right);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
            }
            previousRight = null;
        }
    }
}


/*
Awesome recursive solution that beats 100%

https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/solutions/3669199/java-solution-recursion-beats-100

*/

/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    public Node connect(Node root) {
        if(root == null)return null;
        if(root.left != null && root.right != null){
            root.left.next = root.right;
        }
        if(root.left != null && root.right == null){
            root.left.next = helper(root.next);
        }
        if(root.right != null){
            root.right.next = helper(root.next);
        }
        connect(root.right);
        connect(root.left);
        return root;
    }
    private Node helper(Node root){
        if(root == null)return root;
        if(root.left != null)return root.left;
        if(root.right != null)return root.right;
        return helper(root.next);
    }
}