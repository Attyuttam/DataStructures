//Converted sorted array to binary search tree: https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/description/?envType=study-plan-v2&envId=top-interview-150

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
    private TreeNode sort(int[] nums,int left, int right){
        if(left>right)return null;
        int mid = left + (right-left)/2;

        TreeNode t = new TreeNode(nums[mid]);
        t.left = sort(nums,left,mid-1);
        t.right = sort(nums,mid+1,right);
        return t;
    }
    public TreeNode sortedArrayToBST(int[] nums) {
        return sort(nums,0,nums.length-1);
    }
}