//count complete tree nodes: https://leetcode.com/problems/count-complete-tree-nodes/description/?envType=study-plan-v2&envId=top-interview-150


/*
Algo:
1. Get height of the tree by traversing extreme left, since complete binary tree the distance from tree to the
    leftmost node will give the height of the tree
2. Once we get the height, we find the number of nodes in the last level of the tree by actually traversing the 
	tree which takes O(n) time complexity
3. Then the total number of nodes is = 2^(h-1) -1 + (number of nodes in the last level which we calcualted in step 2)
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
    int ln = 0;
    int h = 0;
    private void getLastLevelNum(TreeNode root, int ch){
        if(root == null)return;
        if(ch == h-1)ln++;
        getLastLevelNum(root.left,ch+1);
        getLastLevelNum(root.right,ch+1);
    }
    public int countNodes(TreeNode root) {
        if(root == null)return 0;
        TreeNode ptr = root;

        while(ptr != null){
            h++;
            ptr = ptr.left;
        }
        getLastLevelNum(root,0);
        return (int)Math.pow(2,h-1) - 1 + ln;
    }
}