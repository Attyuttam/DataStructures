//add binary: https://leetcode.com/problems/add-binary/description/?envType=study-plan-v2&envId=top-interview-150

/*
Saw this solution on leetcode and honestly, I love this approach, the beautiful way in which the carry is handled and
gradually the number is getting formed is honestly beautiful.

Beats 100% for sure
*/

class Solution {
    public String addBinary(String a, String b) {
        StringBuilder res = new StringBuilder();

        int i = a.length() - 1;
        int j = b.length() - 1;

        int c = 0;

        while(i>=0 || j>=0 || c==1){
            if(i>=0){
                c += a.charAt(i)-'0';
                i--;
            }
            if(j>=0){
                c += b.charAt(j)-'0';
                j--;
            }
            res.append(c%2);
            c/=2;
        }
        res.reverse();
        return res.toString();
    }
}