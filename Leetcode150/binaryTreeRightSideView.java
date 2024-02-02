
/*
This is the solution which was intuitive to me and beats 70% users
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
    public List<Integer> rightSideView(TreeNode root) {
        if(root == null)return Collections.emptyList();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        List<Integer> result = new ArrayList<>();
        while(!q.isEmpty()){
            int size = q.size();

            for(int i=0;i<size;i++){
                TreeNode pop = q.poll();
                if(i == size-1){
                    result.add(pop.val);
                }
                if(pop.left != null)q.add(pop.left);
                if(pop.right != null)q.add(pop.right);
            }
        }
        return result;
    }
}

/*
This is the solution which is very intuitive and beats 100% users
https://leetcode.com/problems/binary-tree-right-side-view/solutions/4483447/beats-100-runtime-0ms-java-explained-without-queue

The idea is you simple traverse in a pre order traversal fashion, and you keep track of the level you are at.
If you encounter a level that you have encountered previously, you just need to update the node value at that level
And if you encounter a value that you have never previously seen then the result size will be less than the level
And then you simply add the value to the growing result list

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

    private void bfs(TreeNode root, int level){
        if(root == null)return;
        if(result.size() <= level){
            result.add(root.val);
        }else{
            result.set(level, root.val);
        }
        bfs(root.left, level+1);
        bfs(root.right, level+1);
    }
    public List<Integer> rightSideView(TreeNode root) {
        bfs(root,0);
        return result;
    }
}
