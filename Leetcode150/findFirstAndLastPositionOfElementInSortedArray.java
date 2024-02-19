//find first and last position:https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/?envType=study-plan-v2&envId=top-interview-150

/*
beats 100%, my solution
*/
class Solution {
    private int[] search(int[] nums, int s,int e,int target){
        if(s>e)return new int[]{-1,-1};
        int mid = s + (e-s)/2;

        if(nums[mid] == target){
            int[] left = search(nums,s,mid-1,target);
            int[] right = search(nums,mid+1,e,target);

            if(left[0]==-1 && left[1]==-1){
                if(right[0]==-1 && right[1]==-1){
                    return new int[]{mid,mid};
                }else{
                    return new int[]{mid,right[1]};
                }
            }else if(right[0]==-1 && right[1]==-1){
                if(left[0]==-1 && left[1]==-1){
                    return new int[]{mid,mid};
                }else{
                    return new int[]{left[0],mid};
                }
            }
            
            return new int[]{left[0],right[1]};
        }else if(nums[mid]<target){
            return search(nums,mid+1,e,target);
        }
        return search(nums,s,mid-1,target);
    }
    public int[] searchRange(int[] nums, int target) {
        return search(nums,0,nums.length-1,target);
    }
}