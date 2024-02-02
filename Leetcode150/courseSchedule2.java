//course schedule 2:https://leetcode.com/problems/course-schedule-ii/description/?envType=study-plan-v2&envId=top-interview-150


   class Solution {
    public int[] findOrder(int numCourses, int[][] pq) {
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
        int[] res = new int[numCourses];
        while(!q.isEmpty()){
            int node = q.poll();
            res[tsPos++] = node;

            for(int i=0;i<adj.get(node).size();i++){
                
                if(--ind[adj.get(node).get(i)] == 0)q.add(adj.get(node).get(i));
                
            }
        }
        if(tsPos == numCourses)return res;
        return new int[0];
    }
}
