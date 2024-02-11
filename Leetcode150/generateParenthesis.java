//Generate parenthesis: https://leetcode.com/problems/generate-parentheses/description/?envType=study-plan-v2&envId=top-interview-150

/*
Beats 100%
*/
class Solution {
    List<String> res = new ArrayList<>();

    private void backtrack(int left, int right,StringBuilder s, int n){
        if((left+right)==n*2){
            res.add(s.toString());
            return;
        }

        if(left < n){
            s.append("(");
            backtrack(left+1,right,s,n);
            s.deleteCharAt(s.length()-1);
        }

        if(right<left){
            s.append(")");
            backtrack(left,right+1,s,n);
            s.deleteCharAt(s.length()-1);
        }
    }
    public List<String> generateParenthesis(int n) {
        backtrack(0,0,new StringBuilder(),n);
        return res;
    }
}