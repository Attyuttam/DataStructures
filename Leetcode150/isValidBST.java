//Is valid BST:https://leetcode.com/problems/validate-binary-search-tree/description/?envType=study-plan-v2&envId=top-interview-150


/*
Simple recursive code to check the validity of a BST
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
    public boolean isValidBST(TreeNode root) {
        if(root == null)return true;
        long leftMax = getMax(root.left);
        long rightMin = getMin(root.right);

        if(leftMax >= root.val || rightMin <= root.val)return false;

        return isValidBST(root.left) && isValidBST(root.right);
    }
    private long getMax(TreeNode root){
        if(root == null)return Long.MIN_VALUE;
        TreeNode ptr = root;
        while(ptr.right != null)ptr = ptr.right;
        return ptr.val;
    }

    private long getMin(TreeNode root){
        if(root == null)return Long.MAX_VALUE;
        TreeNode ptr = root;
        while(ptr.left != null)ptr = ptr.left;
        return ptr.val;
    }
}