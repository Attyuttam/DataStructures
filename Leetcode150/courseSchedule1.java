//Course schedule: https://leetcode.com/problems/course-schedule/description/?source=submission-noac

/*
This uses proper DFS and only beats 7.57% users
*/
class Solution {
    private boolean dfs(int n,boolean[] vis, int[][] mat, int[] path){
        boolean res = true;
        vis[n] = true;
        path[n] = 1;
        for(int i=0;i<mat[n].length;i++){
            if(mat[n][i] == 0)continue;
            if(path[i] == 1)return false;
            if(vis[i] == false){
                res = res & dfs(i,vis,mat,path);
                if(res == false)return false;
            }
        }
        path[n]=0;
        return res;
    }
    public boolean canFinish(int numCourses, int[][] pq) {
        if(pq.length == 0)return true;
        int[][] mat = new int[numCourses][numCourses];
        for(int i=0;i<pq.length;i++){
            int n1 = pq[i][0];
            int n2 = pq[i][1];

            if(n1 == n2)return false;//If there is a self loop, return false
            
            mat[n1][n2] = 1;
        }
        boolean[] visited = new boolean[numCourses];
        boolean res = true;
        for(int i=0;i<numCourses;i++){
            if(visited[i] == false){
                res = res & dfs(i, visited, mat, new int[numCourses]);
                if(res == false)return false;
            }
        }
        return true;
    }
}

/*
Fastest, beats 72%
*/

class Solution {
    public boolean canFinish(int numCourses, int[][] pq) {
        if(pq.length == 0)return true;
        int[] ind = new int[numCourses];

        List<List<Integer>> adj = new ArrayList<>();
        for(int i=0;i<numCourses;i++){
            adj.add(new ArrayList<>());
        }
        for(int i=0;i<pq.length;i++){         
            adj.get(pq[i][1]).add(pq[i][0]);
            ind[pq[i][0]]++;
        }
       
        Queue<Integer> q = new LinkedList<>();
        for(int i=0;i<numCourses;i++){
            if(ind[i] == 0)q.add(i);
        }

        int tsPos = 0;

        while(!q.isEmpty()){
            int node = q.poll();
            tsPos++;

            for(int i=0;i<adj.get(node).size();i++){
                
                if(--ind[adj.get(node).get(i)] == 0)q.add(adj.get(node).get(i));
                
            }
        }
        return tsPos == numCourses;
    }
}