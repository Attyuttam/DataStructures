//Find peak element: https://leetcode.com/problems/find-peak-element/description/?envType=study-plan-v2&envId=top-interview-150

/*
My solution, simple Binary search and did not think much on the end constraints.
*/
class Solution {
    private int peak(int[] nums, int s,int e){
        if(s>e)return -1;

        int m = s + (e-s)/2;

        if((m-1<0 && nums[m]>nums[m+1]) || 
            (m+1==nums.length && nums[m]>nums[m-1])){
                return m;
        }
        else if(m-1>=0 && m+1<nums.length && nums[m]>nums[m-1] && nums[m]>nums[m+1]){
            return m;
        }
        
        int val = peak(nums,s,m-1);
        if(val != -1){
            return val;
        }

        return peak(nums,m+1,e);
    }
    public int findPeakElement(int[] nums) {
        if(nums.length == 1)return 0;
        return peak(nums,0,nums.length-1);
    }
}