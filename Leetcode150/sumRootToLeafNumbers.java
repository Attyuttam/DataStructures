//sum root to leaf numbers: https://leetcode.com/problems/sum-root-to-leaf-numbers/description/?envType=study-plan-v2&envId=top-interview-150

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
    int sum = 0;
    public int sumNumbers(TreeNode root) {
        sn(root,0);
        return sum;
    }
    private void sn(TreeNode root, int currentNumber){
        if(root == null)return;
        if(root.left == null && root.right == null){
            sum+=(currentNumber*10 + root.val);
        }
        sn(root.left, currentNumber*10 + root.val);
        sn(root.right, currentNumber*10 + root.val);
    }
}