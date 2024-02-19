//Find minimum in a rotated sorted array: https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/description/?envType=study-plan-v2&envId=top-interview-150

/*
Beats 100%.
only the case where the array size is 2, needs to be handled meticulously.
*/
class Solution {
    public int findMin(int[] nums) {
        if(nums[0] <= nums[nums.length-1])return nums[0];

        int s = 0;
        int e = nums.length-1;
        int mid = -1;

        while(s<=e){
            if(e-s==1){
                if(nums[s]<=nums[e])return nums[s];
                return nums[e];
            }
            mid = s + (e-s)/2;
            if(mid-1>=0 && mid+1<=nums.length-1 && nums[mid]<nums[mid-1] && nums[mid]<nums[mid+1])return nums[mid];
            if(mid-1>=0 && mid+1<=nums.length-1 && nums[mid]>nums[mid-1] && nums[mid]>nums[mid+1])return nums[mid+1];

            if(nums[s] <= nums[mid]){
                s = mid + 1;
            }else{
                e = mid - 1;
            }
        }
        return -1;
    }
}