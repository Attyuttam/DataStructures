//construct binary tree from inorder and post order traversal : https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/description/?envType=study-plan-v2&envId=top-interview-150

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
class Solution {
    int postInd = 0;
    Map<Integer,Integer> hm = new HashMap<>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        postInd = postorder.length - 1;
        for(int i=0;i<inorder.length;i++){
            hm.put(inorder[i],i);
        }
        return bt(inorder,postorder,0,inorder.length-1);
    }

    private TreeNode bt(int[] in, int[] post, int is, int ie){
        if(postInd < 0 || postInd >= post.length || is > ie)return null;

        TreeNode root = new TreeNode(post[postInd]);

        int ip = hm.get(post[postInd]);

        postInd--;
        root.right = bt(in,post,ip+1,ie);
        root.left = bt(in,post,is,ip-1);

        return root;
    }
    
}