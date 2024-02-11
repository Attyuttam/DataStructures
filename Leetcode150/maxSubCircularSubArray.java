//Maximum sum circular subarray:https://leetcode.com/problems/maximum-sum-circular-subarray/description/?envType=study-plan-v2&envId=top-interview-150

/*
Very interesting problem. 
Best explanation: https://algo.monster/liteproblems/918

*/

class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int maxSoFar = 0;
        int minSoFar = 0;
        int maxEndingHere = 0;
        int minEndingHere = 0;

        int maxVal = nums[0];
        int minVal = nums[0];

        int totalSum = 0;

        boolean hasZeroes = false;
        for(int i=0;i<nums.length;i++){
            totalSum+=nums[i];
            if(nums[i] == 0)hasZeroes=true;
        }

        for(int i=0;i<nums.length;i++){
            maxEndingHere += nums[i];
            minEndingHere += nums[i];

            maxVal = Math.max(maxVal,nums[i]);


            if(maxEndingHere < 0){
                maxEndingHere = 0;
            }
            if(minEndingHere > 0){
                minEndingHere = 0;
            }
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
            minSoFar = Math.min(minSoFar, minEndingHere);

        }
        if(!hasZeroes && maxSoFar==0 && minSoFar<0){
            return maxVal;
        }
        return maxSoFar > (totalSum - minSoFar) ? maxSoFar : (totalSum - minSoFar);
    }
}