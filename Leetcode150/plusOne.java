//plus one:https://leetcode.com/problems/plus-one/description/?envType=study-plan-v2&envId=top-interview-150
/*
Beats 5%
*/
class Solution {
    public int[] plusOne(int[] digits) {
        int i = digits.length - 1;

        int c = 1;

        List<Integer> res = new ArrayList<>();

        while(i>=0){
             res.add((digits[i] + c)%10);
             c = (digits[i] + c)/10;
             i--;
        }
        if(c == 1){
            res.add(c);
        }
        Collections.reverse(res);
        return res.stream().mapToInt(v -> v).toArray();
    }
}

/*
This won't work since the number is very large
*/
class Solution {
    public int[] plusOne(int[] digits) {
        int val = 0;
        for(int digit : digits){
            val = val*10 + digit;
        }
        val+=1;
        String res = Integer.toString(val);
        int[] f = new int[res.length()];

        for(int i=0;i<res.length();i++){
            f[i] = res.charAt(i)-'0';
        }
        return f;
    }
}

/*
Beats 100%
Early break !!
*/

class Solution {
    public int[] plusOne(int[] digits) {
        for(int i = digits.length - 1;i>=0;i--){
            if(digits[i]<9){
                digits[i]++;
                return digits;
            }else{
                digits[i] = 0;
            }
        }
        int[] ans = new int[digits.length + 1];
        ans[0] = 1;
        return ans;
    }
}