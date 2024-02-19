//single number 2: https://leetcode.com/problems/single-number-ii/description/?envType=study-plan-v2&envId=top-interview-150

/*
When a number repeats twice, you can simple do XOR to remove the presence of the number

The idea behind this problem is: 

The logical thinking behind this approach is to count the number of 1s at each bit position for all the numbers. Since each number appears three times except for the single number, the sum of 1s at each bit position should be divisible by 3 for a balanced line. Any number of 1s that is not divisible by 3 indicates an unbalanced line, which means the single number contributes to that particular bit position.

By masking the positions of the unbalanced lines with 1s in ans, we effectively isolate the bits that are part of the single number. Finally, the resulting value in ans represents the binary representation of the single number.

*/

class Solution {
    public int singleNumber(int[] nums) {
        int ans = 0;
        for(int i=0;i<32;i++){
            int sum = 0;
            for(int num : nums){
                sum+=((num>>i)&1);
            }
            sum%=3;
            ans = ans | (sum<<i);
        }
        return ans;
    }
}