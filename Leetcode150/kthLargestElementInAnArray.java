//Kth largest element in an array:https://leetcode.com/problems/kth-largest-element-in-an-array/description/?envType=study-plan-v2&envId=top-interview-150
//Excellent explanations: https://leetcode.com/problems/kth-largest-element-in-an-array/solutions/3906260/100-3-approaches-video-heap-quickselect-sorting
/*
Beats 57% in time and 57% in space
*/
class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->b-a);
        for(int i=0;i<nums.length;i++){
            pq.add(nums[i]);
        }
        int val = 0;
        while(k>=1){
            val = pq.poll();
            k--;
        }
        return val;
    }
}

/*
Fixes the space issue
Now it beats 68% users in time and 69% users in space
THIS IS THE BEST I COULD DO
*/

class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=0;i<k;i++){
            pq.add(nums[i]);
        }
        while(k<nums.length){
			if(nums[k]<=pq.peek()){
                k++;
                continue;
            }
            pq.poll();
			pq.add(nums[k]);
            k++;
        }
        return pq.peek();
    }
}

/*
This is another solution based on quick sort but its pretty slow
*/

class Solution {
    private int partition(int[] nums, int s, int e){
        int pos = s;
        int pivot = nums[pos];
        int i = s;
        int j = e;

        while(i<j){
            while(i<=e && nums[i]<=pivot){
                i++;
            }
            while(j>=s && nums[j]>pivot){
                j--;
            }
            if(i<j){
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        int temp = nums[j];
        nums[j] = nums[pos];
        nums[pos] = temp;
        return j;
    }
    public int findKthLargest(int[] nums, int k) {
        if(nums.length == 1)return nums[0];
        int s = 0;
        int e = nums.length - 1;

        while(true){
            int pivotIndex = partition(nums,s,e);
            if(pivotIndex == nums.length - k){
                return nums[pivotIndex];
            }else if(pivotIndex > nums.length-k){
                e = pivotIndex - 1;
            }else{
                s = pivotIndex + 1;
            }
        }
    }
}