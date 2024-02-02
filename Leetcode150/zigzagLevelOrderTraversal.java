//Zig Zag traversal: https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/description/?envType=study-plan-v2&envId=top-interview-150

/*
Use DFS for all level order traversals ! They are wayyyyyy faster
Using a queue makes the process slpw.
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
    List<List<Integer>> result = new ArrayList<>();
    private void dfs(TreeNode root, int level){
        if(root == null)return;

        List<Integer> temp;
        if(result.size() <= level){
            temp = new ArrayList<>();
            temp.add(root.val);
            result.add(temp);
        }else{
            temp = result.get(level);
            temp.add(root.val);
            result.set(level, temp);
        }

        dfs(root.left, level + 1);
        dfs(root.right, level + 1);

    }
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        dfs(root, 0);
        for(int i=0;i<result.size();i++){
            if(i % 2 != 0){
                Collections.reverse(result.get(i));
            }
        }
        return result;
    }
}