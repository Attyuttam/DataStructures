//combination sum:https://leetcode.com/problems/combination-sum/description/?envType=study-plan-v2&envId=top-interview-150

/*
Beats 99.91%
*/
class Solution {
    List<List<Integer>> res = new ArrayList<>();

    private void backtrack(int[] c, int pos, int target, List<Integer> tempres){
        if(target == 0){
            res.add(new ArrayList<>(tempres));
            return;
        }

        for(int i=pos;i<c.length;i++){
            if(target < c[i])continue;
            tempres.add(c[i]);
            backtrack(c,i,target-c[i],tempres);
            tempres.remove(tempres.size()-1);
        }
    }
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        backtrack(candidates, 0,target, new ArrayList<>());
        return res;
    }
}