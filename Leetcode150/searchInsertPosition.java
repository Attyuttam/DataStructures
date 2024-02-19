//Search insert position:https://leetcode.com/problems/search-insert-position/description/?envType=study-plan-v2&envId=top-interview-150

/*
My solution. Beats 100%. Constructive thinking, you have to think intuitively
*/

class Solution {
    public int searchInsert(int[] nums, int target) {
        if(nums.length==0||target<nums[0])return 0;
        if(target>nums[nums.length-1])return nums.length;

        int s = 0;
        int e = nums.length-1;
        int mid=0;
        while(s<=e){
            mid = s + (e-s)/2;

            if(s==e){
                if(nums[mid] == target)break;
                if(nums[mid] < target){
                    mid++;
                    break;
                }else{
                    break;
                }
            }

            if(nums[mid] == target)break;
            if(nums[mid] < target)s = mid + 1;
            else e = mid - 1;
        }
        return mid;
    }
}