//Simplify path: https://leetcode.com/problems/simplify-path/description/?envType=study-plan-v2&envId=top-interview-150

//Used the 2 stack approach to solve this and its pretty interesting
class Solution {
    public String simplifyPath(String path) {
        Stack<String> st1 = new Stack<String>();
        Stack<String> st2 = new Stack<String>();

        String[] split = path.split("/");

        for(int i=split.length-1;i>=0;i--){
            if(!split[i].equals("") && !split[i].equals(".")){
                st1.push(split[i]);
            }
        }

        int goBackCount = 0;
        while(!st1.isEmpty()){
            String topVal = st1.pop();
        
            if(topVal.equals("..")){
                if(!st2.isEmpty())st2.pop();
            }else{
                st2.push(topVal);
            }
            
        }

        StringBuilder result = new StringBuilder();
        while(!st2.isEmpty()){
            result.insert(0,st2.pop());
            result.insert(0,"/");
        }
        if(result.length() == 0 )
            result.append("/");
        return result.toString();
    }
}