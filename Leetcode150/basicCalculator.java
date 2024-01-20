//Basic calculator: https://leetcode.com/problems/basic-calculator/?envType=study-plan-v2&envId=top-interview-150

/*
The solution below is what I came up with, it passes 33/45 test cases but it fails for this test case:

(7) - (0) + (4) since my code returns 3 but the real answer should be 11

*/
class Solution {
    public int performOp(int val1, int val2,char op){
        if(op == '+')return val1+val2;
        return val1-val2;
    }
    public boolean isDigit(char val){
        if(val-'0'>=0 && val-'0'<=9)return true;
        return false;
    }
    public int getNumber(Stack<Integer> s){
        int val = 0;
        while(!s.isEmpty()){
            val = val*10 + s.pop();
        }
        return val;
    }
    public int calculate(String s) {
        Stack<Character> paren = new Stack<>();
        Stack<Integer> number = new Stack<>();
        Stack<Character> operator = new Stack<>();

        for(int i=s.length()-1;i>=0;i--){
            char currChar = s.charAt(i);
            
            if(currChar == ' ')continue;
            else if(currChar == ')') paren.push(currChar);
            else if(currChar == '+' || currChar == '-'){
                operator.push(currChar);
            }else if(currChar == '('){
                paren.pop();
                int val1 = number.pop();

                if(number.isEmpty()){
                    if(operator.isEmpty()){
                        number.push(val1);
                    }else{
                        number.push(-1*val1);
                        operator.pop();
                    }
                }else{
                    int val2 = number.pop();
                    number.push(performOp(val1, val2, operator.pop()));
                }
            }else if(isDigit(currChar)){
                int pos = i;

                Stack<Integer> num = new Stack<>();
                while(pos>=0 && isDigit(s.charAt(pos))){
                    num.push((s.charAt(pos) - '0'));
                    pos--;
                }
                if(pos!=i)i=pos+1;
                number.push(getNumber(num));
            }
        }
        while(!number.isEmpty() && !operator.isEmpty()){
            int val1 = number.pop();
            if(number.isEmpty()){
                return -1 * val1;
            }
            int val2 = number.pop();

            number.push(performOp(val1, val2, operator.pop()));
        }
        return number.pop();
    }
}

//This is the solution I saw over in the solutions section: https://leetcode.com/problems/basic-calculator/solutions/2831476/java-solution-using-stack-with-o-n

class Solution {
    private boolean isDigit(char ch){
        return ((ch - '0') >= 0) && ((ch - '0') <= 9);
    }
    public int calculate(String s) {
        int sum = 0;
        int sign = 1;

        Stack<Integer> st = new Stack<>();

        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);

            if(isDigit(ch)){
                int pos = i;
                long val = 0;

                while(pos < s.length() && isDigit(s.charAt(pos))){
                    val = val*10 + (s.charAt(pos) - '0');
                    pos++;
                }
                i = pos-1;
                val *= sign;
                sign = 1;

                sum += val;
            }else if(ch == '-'){
                sign *= -1;
            }else if(ch == '('){
                st.push(sum);
                st.push(sign);

                sum = 0;
                sign = 1;
            }else if(ch == ')'){
                sum *= st.pop();
                sum += st.pop();
            }
        }
        return sum;
    }
}