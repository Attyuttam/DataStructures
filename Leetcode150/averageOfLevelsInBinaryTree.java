//Average levels of a binary tree: https://leetcode.com/problems/average-of-levels-in-binary-tree/description/?envType=study-plan-v2&envId=top-interview-150
/*
Same intuition as right side view of a binary tree which uses DFS rather than BFS.
This solution beat 96% users
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
class Avg{
    public long levelSum;
    public long numNodes;

    public Avg(long levelSum, long numNodes){
        this.levelSum = levelSum;
        this.numNodes = numNodes;
    }
}
class Solution {
    List<Avg> result = new ArrayList<>();
    private void dfs(TreeNode root, int level){
        if(root == null)return;

        if(result.size() <= level){
            result.add(new Avg(root.val, 1));
        }else{
            Avg val = result.get(level);
            val.numNodes++;
            val.levelSum += root.val;
            result.set(level, val);
        }

        dfs(root.left, level+1);
        dfs(root.right, level+1);
    }
    public List<Double> averageOfLevels(TreeNode root) {
        dfs(root,0);
        return result.stream().map(v -> (v.levelSum/(double)v.numNodes)).collect(Collectors.toList());
    }
}