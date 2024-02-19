//Find median of two sorted arrays: https://leetcode.com/problems/median-of-two-sorted-arrays/description/?envType=study-plan-v2&envId=top-interview-150

/*
Saw striver's youtube video for this. This is a brain twister for sure, definitely makes you think a lot
*/
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1.length > nums2.length)return findMedianSortedArrays(nums2,nums1);
        int numElementsInFirstHalf = (nums1.length + nums2.length)%2 == 0 ? ((nums1.length + nums2.length)/2) : ((nums1.length + nums2.length + 1)/2);

        //s and e will denote the size of the first partition
        //s is the minimum number of elements that can be picked from nums1
        //e is the maximum number of elements that can be picked from nums1
        int s = 0;
        int e = nums1.length;//not an index but the max number of elements that can be picked from nums1

        /*        ea1  sa1
            0  1  2    3  4  5
            x1 x2 x3 | x4 x5 x6
               y2 y3 | y4 y5
                0  1    2  3
                  ea2  sa2
        */
        while(s<=e){
            int numEl1 = (s + (e-s)/2);
            int numEl2 = numElementsInFirstHalf - numEl1;

            int ea1 = numEl1 == 0? Integer.MIN_VALUE : nums1[numEl1-1];
            int ea2 = numEl2 == 0? Integer.MIN_VALUE : nums2[numEl2-1];
            int sa1 = numEl1 >= nums1.length? Integer.MAX_VALUE : nums1[numEl1];
            int sa2 = numEl2 >= nums2.length? Integer.MAX_VALUE : nums2[numEl2];

            if(ea1 <= sa2 && ea2 <= sa1){
                if((nums1.length + nums2.length)%2 == 0){
                    return (Math.max(ea1,ea2) + Math.min(sa1,sa2)) / 2.0;
                }else{
                    return Math.max(ea1,ea2);
                }
            }else if(ea1 > sa2){
                e = numEl1-1;
            }else{
                s = numEl1 + 1;
            }
        }
        return 0;
    }
}