//Minimum absolute difference: https://leetcode.com/problems/minimum-absolute-difference-in-bst/description/?envType=study-plan-v2&envId=top-interview-150

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
    TreeNode prev = null;
    int minDiff = Integer.MAX_VALUE;
    private void ino(TreeNode root){
        if(root == null)return;
        ino(root.left);

        if(prev != null){
            minDiff = Math.min(minDiff, Math.abs(root.val - prev.val));
        }
        prev = root;
        ino(root.right);
    }
    public int getMinimumDifference(TreeNode root) {
        ino(root);
        return minDiff;
    }
}