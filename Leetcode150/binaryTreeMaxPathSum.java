//Binary tree max path sum: https://leetcode.com/problems/binary-tree-maximum-path-sum/description/?envType=study-plan-v2&envId=top-interview-150

/*
This solution could beat only 6% of users
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
    public int maxPathSum(TreeNode root) {
        if(root == null)return Integer.MIN_VALUE;

        int vl = Math.max(0,findmax(root.left));
        int vr = Math.max(0,findmax(root.right));

        return Math.max(vl + root.val + vr, Math.max(maxPathSum(root.left),maxPathSum(root.right)));
    }
    private int findmax(TreeNode root){
        if(root == null)return Integer.MIN_VALUE;

        return Math.max(0,Math.max(findmax(root.left), findmax(root.right))) + root.val;
    }
}
/*
Applied DP but did not help much
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
    Map<TreeNode, Integer> dp = new HashMap<>();
    Map<TreeNode, Integer> dpMps = new HashMap<>();

    public int maxPathSum(TreeNode root) {
        if(root == null)return Integer.MIN_VALUE;
        if(dpMps.get(root) != null)return dpMps.get(root);

        int vl = Math.max(0,findmax(root.left));
        int vr = Math.max(0,findmax(root.right));

        dpMps.put(root,Math.max(vl + root.val + vr, Math.max(maxPathSum(root.left),maxPathSum(root.right))));
        return dpMps.get(root);
    }
    private int findmax(TreeNode root){
        if(root == null)return Integer.MIN_VALUE;

        if(dp.get(root) != null)return dp.get(root);

        dp.put(root,Math.max(0,Math.max(findmax(root.left), findmax(root.right))) + root.val);
        return dp.get(root);
    }
}
/*
Fastest solution ,beats 10% users
The idea that is being used here is to not use a separate findmax method, but to use the findmax method 
itself to update the current maximum path that might pass via the root and then return only the value 
that is left + root or right + root.

This is how you save on the method calls thereby making the code much faster
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
    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {   
        mps(root);
        return max;
    }
    private int mps(TreeNode root) {
        if(root == null)return 0;

        int vl = Math.max(0,mps(root.left));
        int vr = Math.max(0,mps(root.right));

        max = Math.max(max, vl + root.val + vr);

        return Math.max(vl,vr) + root.val;
    }
}