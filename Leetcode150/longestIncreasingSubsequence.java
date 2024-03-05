//Longest Increasing subsquence: 

/*
The Brute Force approach. Hits TLE
*/

class Solution {
    int max = -1;
    public int lengthOfLIS(int[] nums) {
        if(nums.length == 0 || nums.length == 1)return nums.length;

        lis(0,new ArrayList<>(),nums);
        return max;
    }
    private void lis(int ind, List<Integer> sub, int[] nums){
        if(ind == nums.length){
            max = Math.max(max, sub.size());
            return;
        }
        if(sub.size() == 0 || nums[ind] > sub.get(sub.size()-1)){
            sub.add(nums[ind]);
            lis(ind+1,sub,nums);
            sub.remove(sub.size()-1);
        }

        lis(ind+1,sub,nums);
    }

}