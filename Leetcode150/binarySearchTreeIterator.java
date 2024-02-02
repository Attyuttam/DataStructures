//binary search tree iterator: https://leetcode.com/problems/binary-search-tree-iterator/description/?envType=study-plan-v2&envId=top-interview-150

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class BSTIterator {
    Queue<Integer> list;

    private void getInOrder(TreeNode root){
        if(root != null){
            getInOrder(root.left);
            list.add(root.val);
            getInOrder(root.right);
        }
    }
    public BSTIterator(TreeNode root) {
        list = new LinkedList<>();
        getInOrder(root);
    }
    
    public int next() {
        return list.poll();
    }
    
    public boolean hasNext() {
        return !list.isEmpty();
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */