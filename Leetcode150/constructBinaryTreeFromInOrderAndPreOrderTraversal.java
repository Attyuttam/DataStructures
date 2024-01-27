//Construct binary tre from inorder and preorder traversal: https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/?envType=study-plan-v2&envId=top-interview-150

/*
This is my solution which could beat only 47% users
I am very sure this is beacuse of the for loop required to find the element in the inorder traversal every single time
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
    int preInd = 0;
    public TreeNode buildTree(int[] pre, int[] in) {
        return bt(in,pre,0,in.length-1);
    }
    private int find(int val , int[] in){
        for(int i=0;i<=in.length-1;i++){
            if(in[i] == val)return i;
        }
        return -1;
    }
    private TreeNode bt(int[] in, int[] pre, int is, int ie){
        if(preInd>=0 && preInd<pre.length && is<=ie){
            TreeNode root = new TreeNode(pre[preInd]);
            int ip = find(pre[preInd],in);
            preInd++;
            if(ip != -1){
                root.left = bt(in,pre,is,ip-1);
                root.right = bt(in,pre,ip+1,ie);
            }

            return root;
        
        }else{
            return null;
        }
    }
}

/*
Read the constraints and it said that it is guaranteed that the elements in the pre and in order traversal 
will be unique so just replacing the for loop with a hashmap made my solution beat 71% users
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
    int preInd = 0;
    Map<Integer, Integer> hm = new HashMap<>();

    public TreeNode buildTree(int[] pre, int[] in) {
        for(int i=0;i<in.length;i++){
            hm.put(in[i],i);
        }
        return bt(in,pre,0,in.length-1);
    }

    private TreeNode bt(int[] in, int[] pre, int is, int ie){
        if(preInd>=0 && preInd<pre.length && is<=ie){
            TreeNode root = new TreeNode(pre[preInd]);
            int ip = hm.getOrDefault(pre[preInd],-1);
            preInd++;
            if(ip != -1){
                root.left = bt(in,pre,is,ip-1);
                root.right = bt(in,pre,ip+1,ie);
            }

            return root;
        
        }else{
            return null;
        }
    }
}

/*
Everytime when I am extracting the position of the element in the inorder traversal from the hashmap
I was using getorDefault() and if the element is not avaialabel the default value reuturned was -1

So, I had also instileld a -1 check but that is unnecessary because the elements will always be available !

Removing the if statement made me beat 95% users
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
    int preInd = 0;
    Map<Integer, Integer> hm = new HashMap<>();

    public TreeNode buildTree(int[] pre, int[] in) {
        for(int i=0;i<in.length;i++){
            hm.put(in[i],i);
        }
        return bt(in,pre,0,in.length-1);
    }

    private TreeNode bt(int[] in, int[] pre, int is, int ie){
        if(preInd<0 || preInd>=pre.length || is>ie)return null;
        
        TreeNode root = new TreeNode(pre[preInd]);
        int ip = hm.get(pre[preInd]);
        preInd++;
        
        root.left = bt(in,pre,is,ip-1);
        root.right = bt(in,pre,ip+1,ie);
        
        return root;
        
    }
}