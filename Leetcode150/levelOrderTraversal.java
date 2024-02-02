//binary tree level order traversal

/*
This is the most trivial solution that uses queue to work in a BFS fashion.
But this solution could beat only 23.6% users
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
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null)return Collections.emptyList();
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> tempResult= new ArrayList<>();

        Queue<TreeNode> q = new LinkedList<>();

        q.add(root);

        while(!q.isEmpty()){
            int size = q.size();

            for(int i=0;i<size;i++){
                TreeNode temp = q.poll();
                tempResult.add(temp.val);                

                if(temp.left != null)q.add(temp.left);
                if(temp.right != null)q.add(temp.right);
            }
            result.add(new ArrayList<>(tempResult));
            tempResult.clear();
        }
        return result;
    }
}

/*

I am getting new revelations, till date, level order traversal meant BFS for me
This is the level order traversal using DFS that beat 100% users.
Most likely because queue operations take time and DFS uses internal stack which is much faster
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
    public List<List<Integer>> levelOrder(TreeNode root) {
        dfs(root, 0);
        return result;
    }
}