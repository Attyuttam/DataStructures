//word break: 

/*
Simple recursion, gives TLE
*/
class Solution {
    List<String> globalWordDict;
    String globalS;
    public boolean wordBreak(String s, List<String> wordDict) {
        globalS = s;
        globalWordDict = wordDict;

        return wordBreakHelper(0,globalS.length()-1);
    }
    private boolean wordBreakHelper(int b, int e){
        if(b<0 || e>=globalS.length())return false;

        if(globalWordDict.contains(globalS.substring(b,e+1)))return true;

        boolean isFound = false;
        for(int i=b;i<e;i++){
            isFound = wordBreakHelper(b,i) && wordBreakHelper(i+1,e);
            if(isFound)return true;
        }
        return false;
    }
}

/*
With memoization, but beats just 5%
*/
class Solution {
    List<String> globalWordDict;
    String globalS;
    Map<String,Boolean> dp;
    public boolean wordBreak(String s, List<String> wordDict) {
        globalS = s;
        globalWordDict = wordDict;
        dp = new HashMap<>();

        return wordBreakHelper(0,globalS.length()-1);
    }
    private boolean wordBreakHelper(int b, int e){
        if(b<0 || e>=globalS.length())return false;

        if(dp.get(globalS.substring(b,e+1))!=null){
            return dp.get(globalS.substring(b,e+1));
        }
        if(globalWordDict.contains(globalS.substring(b,e+1))){
            dp.put(globalS.substring(b,e+1), true);
            return true;
        }

        boolean isFound = false;
        for(int i=b;i<e;i++){
            isFound = wordBreakHelper(b,i) && wordBreakHelper(i+1,e);
            if(isFound){
                dp.put(globalS.substring(b,e+1), true);
                return true;
            }
        }
        dp.put(globalS.substring(b,e+1), false);
        return false;
    }
}

/*
In word break problem, you have to shift the idea to not breaking the word itself and cehcking if the broken
pieces are there in the dictionary to actually using each word from the dictionary and checking if the string starts with the 
given word from the dictionary or not and then recursing on that.

This solution beat 99%
*/

class Solution {
    Boolean[] dp;
    HashSet<String> wordSet;
    String globalS;
    public boolean wordBreak(String s, List<String> wordDict) {
        dp = new Boolean[s.length()+1];
        wordSet = new HashSet<>(wordDict);
        globalS = s;
        for(int i=0;i<=s.length();i++){
            dp[i] = null;
        }
        return wordBreakHelper(0);
    }
    private boolean wordBreakHelper(int pos){
        if(pos == globalS.length())return true;

        if(dp[pos]!=null)return dp[pos];

        boolean found = false;
        for(String word : wordSet){
            if(globalS.startsWith(word, pos)){
                found = wordBreakHelper(pos+word.length());
                if(found){
                    dp[pos] = true;
                    return true;
                }
            }
        }
        dp[pos] = false;
        return false;
    }
}