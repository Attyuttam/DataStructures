//Flatten a binary tree: https://leetcode.com/problems/flatten-binary-tree-to-linked-list/submissions/1157954169/?envType=study-plan-v2&envId=top-interview-150

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
    public void flatten(TreeNode root) {
        if(root == null)return;
        if(root.left != null){
            TreeNode ptr = root.left;
            while(ptr.right!=null)ptr=ptr.right;
            ptr.right = root.right;
            root.right = root.left;
            root.left = null;
        }
        flatten(root.right);
    }
}

/*

This is the most beautiful solution that I got from one of the candidates I had interviewed, very easy to udnerstand
*/

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
    public TreeNode flattenhelper(TreeNode root) {
        if(root == null || root.left == null && root.right == null)return root;
        
        TreeNode leftFlat = flattenhelper(root.left);
        TreeNode rightFlat = flattenhelper(root.right);

        TreeNode curr = root;
        curr.left = null;
        curr.right = leftFlat;

        while(curr.right!=null){
            curr = curr.right;
        }

        curr.right = rightFlat;
        return root;
    }
    public void flatten(TreeNode root){
        flattenhelper(root);
    }
}