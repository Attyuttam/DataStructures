//Summary ranges: https://leetcode.com/problems/summary-ranges/?envType=study-plan-v2&envId=top-interview-150

class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();

        for(int i=0;i<nums.length;i++){
            int s = nums[i];
            int j = i+1;

            while(j<nums.length && nums[j]-1 == nums[j-1])j++;

            if(j == i+1){
                result.add(String.valueOf(s));
            }else{
                result.add(String.valueOf(s)+ "->" +String.valueOf(nums[j-1]));
            }
            i=j-1;

        }
        return result;
    }
}