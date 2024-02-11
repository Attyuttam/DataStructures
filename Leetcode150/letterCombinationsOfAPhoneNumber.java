//Letter combinations of a phone number: https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/?envType=study-plan-v2&envId=top-interview-150

/*
Simple backtracking that beats 72.5%
*/

class Solution {
    List<List<Character>> digitToChar = new ArrayList<>();
    List<String> res = new ArrayList<>();

    private void backtrack(String digits, int pos, StringBuilder str){
        if(pos == digits.length()){
            if(!("".equals(str.toString())))
                res.add(str.toString());
            return;
        }

        for(int i=0;i<digitToChar.get(digits.charAt(pos)-'0').size();i++){
            char c = digitToChar.get(digits.charAt(pos)-'0').get(i);
            str.append(c);
            backtrack(digits,pos+1,str);
            str.deleteCharAt(str.length()-1);
        }

    }
    public List<String> letterCombinations(String digits) {
        
        
        digitToChar.add(new ArrayList<>());
        digitToChar.add(new ArrayList<>());
        digitToChar.add(Arrays.asList('a','b','c'));
        digitToChar.add(Arrays.asList('d','e','f'));
        digitToChar.add(Arrays.asList('g','h','i'));
        digitToChar.add(Arrays.asList('j','k','l'));
        digitToChar.add(Arrays.asList('m','n','o'));
        digitToChar.add(Arrays.asList('p','q','r','s'));
        digitToChar.add(Arrays.asList('t','u','v'));
        digitToChar.add(Arrays.asList('w','x','y','z'));

        backtrack(digits,0,new StringBuilder());

        return res;
    }
}