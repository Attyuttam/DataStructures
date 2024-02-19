//Search in a rotated sorted array: https://leetcode.com/problems/search-in-rotated-sorted-array/description/?envType=study-plan-v2&envId=top-interview-150


/*
This solution beats 100% users and I took it from the solutions section. 
This solution does not seem much intuitive to me.
What it is doing is essentially leveraging the sorted section to see if the target is within that or not.
If not, its moving to the other section

So, the idea is we move to the sorted section and check the availability of target within this section using the sorted properties.
nums[low] <= target && target<=nums[mid] (for the left half and vice versa)

if it is not within the sorted section check the other section.

The idea is to not waste time finding the pivot but rather move to the sorted sections and check for target there itself.
*/
class Solution {
    public int search(int[] nums, int target) {
        int s = 0;
        int e = nums.length-1;
        int mid=-1;
        while(s<=e){
            mid = s + (e-s)/2;

            if(nums[mid] == target)return mid;
			
			//checking if the left half is sorted
            if(nums[s] <= nums[mid]){
			
				//yes its sorted
				//since its sorted, we are using the sorted array properties to confirm if the target is within this sorted section or not
                if(nums[s]<=target && target<nums[mid]){
					//yes, target is within the sorted bounds
                    e=mid-1;
                }else{
					//no, the target is not in this section.
                    s=mid+1;
                }
            }
            else{
				//since the left half is not sorted, the right half is definitely sorted
                if(nums[mid]<target && target<=nums[e]){
                    s=mid+1;
                }else{
                    e=mid-1;
                }
            }
        }
        return -1;
    }
}