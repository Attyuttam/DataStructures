//kth smallest element in a BST:https://leetcode.com/problems/kth-smallest-element-in-a-bst/description/?envType=study-plan-v2&envId=top-interview-150

/*
This is a very intuitive but trivial solution that could beat only 45.33% users
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
    List<Integer> result = new ArrayList<>();

    private void ino(TreeNode root){
        if(root != null){
            ino(root.left);
            result.add(root.val);
            ino(root.right);
        }
    }
    public int kthSmallest(TreeNode root, int k) {
        ino(root);
        return result.get(k-1);
    }
}

/*

i only developed this solution that beats 100% users.

It sort of seems that recursion is the fastest in tree related problems
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
    int val = -1;
    boolean flag = false;
    int kth = -1;
    int pos = 0;

    private void ino(TreeNode root){
        
        if(root != null && flag == false){
            ino(root.left);

            pos++;
            if(pos == kth){
                val = root.val;
                flag = true;
                return;
            }
            
            ino(root.right);
        }

    }

    public int kthSmallest(TreeNode root, int k) {
        kth = k;
        ino(root);
        return val;
    }
}