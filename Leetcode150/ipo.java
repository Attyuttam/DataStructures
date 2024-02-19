//IPO: https://leetcode.com/problems/ipo/description/?envType=study-plan-v2&envId=top-interview-150

/*
Brute force solution. Gets TLE at 32/35 test case

I understand that I need a heap here because everytime I need to pick up the project with the maximum profit but the constraint
is that I cannot pick up an element that has a capital more than the capital I currently hold. How do I use that to ensure the 
correct element is extracted from the heap everytime.

*/
class Solution {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        int cc = w;
        Set<Integer> doneprojects = new HashSet<>();
        for(int p=1;p<=k;p++){
            int maxprofit = -1;
            int ind = -1;
            for(int i=0;i<n;i++){
                if(!doneprojects.contains(i) && profits[i] > maxprofit && capital[i]<=cc){
                    maxprofit = profits[i];
                    ind = i;
                }
            }
            if(ind != -1){
                cc+=maxprofit;
                doneprojects.add(ind);
            }
        }
        return cc;
    }
}

/*
Beats 50%
*/
class Solution {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        int projects[][] = new int[n][2];
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> b-a);

        for(int i=0;i<n;i++){
            projects[i] = new int[]{profits[i], capital[i]};
        }

        //sort the projects on the basis of their capital
        Arrays.sort(projects, (a,b) -> a[1] - b[1]);

        int i = 0;
        int nw = w;

        while(k>0){
            while(i<projects.length && w>=projects[i][1]){
                pq.add(projects[i][0]);
                i++;
            }
            if(!pq.isEmpty()){
                nw+=pq.poll();
                k--;
            }
            if(nw == w){
                break;
            }else{
                w = nw;
            }
        }
        return w;
    }
}
/*
Beats 56%
*/
class Solution {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        int projects[][] = new int[n][2];
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> b-a);

        for(int i=0;i<n;i++){
            projects[i] = new int[]{profits[i], capital[i]};
        }

        //sort the projects on the basis of their capital
        Arrays.sort(projects, (a,b) -> a[1] - b[1]);

        int i = 0;

        while(k>0){
            while(i<projects.length && w>=projects[i][1]){
                pq.add(projects[i][0]);
                i++;
            }
            if(!pq.isEmpty()){
                w+=pq.poll();
            }
			k--;
        }
        return w;
    }
}