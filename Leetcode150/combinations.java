//combinations:https://leetcode.com/problems/combinations/description/?envType=study-plan-v2&envId=top-interview-150

/*
My solution could beat only 79%
*/
class Solution {
    List<List<Integer>> res = new ArrayList<>();
    private void backtrack(int num, int n, int k, int size, List<Integer> tempres){
        if(size == k){
            res.add(new ArrayList<>(tempres));
            return;
        }
        for(int i=num;i<=n;i++){
            tempres.add(i);
            if(num <= n)
                backtrack(i+1,n,k,size+1,tempres);
            tempres.remove(tempres.size()-1);
        }
    }
    public List<List<Integer>> combine(int n, int k) {
        backtrack(1,n,k,0,new ArrayList<>());
        return res;
    }
}

/*
From the internet, could beat 96%
*/

class Solution {
    List<List<Integer>> res = new ArrayList<>();
    private void backtrack(int num, int n, int k, List<Integer> tempres){
        if(k == 0){
            res.add(new ArrayList<>(tempres));
            return;
        }
        for(int i=num;i<=(n-k+1);i++){
            tempres.add(i);
            backtrack(i+1,n,k-1,tempres);
            tempres.remove(tempres.size()-1);
        }
    }
    public List<List<Integer>> combine(int n, int k) {
        backtrack(1,n,k,new ArrayList<>());
        return res;
    }
}