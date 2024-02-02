//path sum: https://leetcode.com/problems/path-sum/description/?envType=study-plan-v2&envId=top-interview-150


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
    public boolean hasPathSum(TreeNode root, int targetSum) {
        return psHelper(root,targetSum,0);
    }

    private boolean psHelper(TreeNode root, int ts, int ps){
        if(root == null)return false;
        if(root.left == null && root.right == null)return ts == ps+root.val;

        if(psHelper(root.left, ts, ps+root.val))return true;
        if(psHelper(root.right, ts, ps+root.val))return true;

        return false;
    }
}