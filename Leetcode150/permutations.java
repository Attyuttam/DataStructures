//permutations: https://leetcode.com/problems/permutations/description/?envType=study-plan-v2&envId=top-interview-150

/*
My solution ,beats 95%
*/
class Solution {
    List<List<Integer>> res = new ArrayList<>();
    private void backtrack(int[] nums, int[] vis, int size, List<Integer> tempres){
        if(size == nums.length){
            res.add(new ArrayList<>(tempres));
            return;
        }

        for(int i=0;i<nums.length;i++){
            if(vis[i] == 0){
                vis[i] = 1;
                tempres.add(nums[i]);
                backtrack(nums,vis,size+1,tempres);
                tempres.remove(tempres.size()-1);
                vis[i] = 0;
            }
        }
    }
    public List<List<Integer>> permute(int[] nums) {
        backtrack(nums,new int[nums.length],0,new ArrayList<>());
        return res;
    }
}