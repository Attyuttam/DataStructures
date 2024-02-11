//construct quad tree: https://leetcode.com/problems/construct-quad-tree/description/?envType=study-plan-v2&envId=top-interview-150

/*
My solution that is flawed, the issue is in my solution, even if the grid is let say 8x8, I am still breaking it down and iterating on each piece
This is create a root node with all leaf nodes, but that is incorrect as ideally if the grid 8x8 has all values same there should be only one root.
And that is exactly the base condition which has been done in the solution that I got from the internet and has been provided below
*/

/*
// Definition for a QuadTree node.
class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;

    
    public Node() {
        this.val = false;
        this.isLeaf = false;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }
    
    public Node(boolean val, boolean isLeaf) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }
    
    public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }
};
*/

class Solution {
    private Node cons(int[][] grid,int n,int x,int y){
        int mid = n/2;

        if(n==1)return new Node(grid[x][y]==1?true:false,true);
        
        if(n==2){
            Node root = new Node();
            if(grid[x][y]==grid[x+1][y] && 
                grid[x+1][y]==grid[x+1][y+1] && 
                grid[x][y+1]==grid[x+1][y+1]){

                root.val = grid[x][y]==1?true:false;
                root.isLeaf = true;
                return root;

            }else{
                root.val = false;
            }
            
            root.topLeft = new Node(grid[x][y]==1?true:false,true);
            root.topRight = new Node(grid[x][y+1]==1?true:false,true);
            root.bottomLeft = new Node(grid[x+1][y]==1?true:false,true);
            root.bottomRight = new Node(grid[x+1][y+1]==1?true:false,true);
            root.isLeaf = false;
        
            return root;
        }

        Node root = new Node();

        root.topLeft = cons(grid,n/2,x,y);
        root.topRight = cons(grid,n/2,x,y+(n/2));
        root.bottomLeft = cons(grid,n/2,x+(n/2),y);
        root.bottomRight = cons(grid,n/2,x+(n/2),y+(n/2));

        root.val = getValue(root);
        root.isLeaf = false;
        return root;
    }
    private boolean getValue(Node root){
        if(root.topLeft.val == root.topRight.val && root.topRight.val==root.bottomRight.val && root.bottomRight.val==root.bottomLeft.val){
            return root.topLeft.val;
        }
        return false;
    }
    public Node construct(int[][] grid) {
        return cons(grid,grid.length,0,0);
    }
}
/*

Saw this solution on the internet. Beats 100%, very much close to mine, I was just not being able to decipher the base condition
*/

/*
// Definition for a QuadTree node.
class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;

    
    public Node() {
        this.val = false;
        this.isLeaf = false;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }
    
    public Node(boolean val, boolean isLeaf) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }
    
    public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }
};
*/

class Solution {
    private Node cons(int[][] grid,int n,int x,int y){
        if(allSame(grid,n,x,y))
            return new Node(grid[x][y]==1?true:false, true);

        Node root = new Node(true,false);

        root.topLeft = cons(grid,n/2,x,y);
        root.topRight = cons(grid,n/2,x,y+(n/2));
        root.bottomLeft = cons(grid,n/2,x+(n/2),y);
        root.bottomRight = cons(grid,n/2,x+(n/2),y+(n/2));

        return root;
    }
    private boolean allSame(int[][] grid,int n,int x,int y){
        int endx = x + n;
        int endy = y + n;
        int val = grid[x][y];
        for(int i=x;i<endx;i++){
            for(int j=y;j<endy;j++){
                if(grid[i][j] != val)return false;
            }
        }
        return true;
    }
    public Node construct(int[][] grid) {
        return cons(grid,grid.length,0,0);
    }
}