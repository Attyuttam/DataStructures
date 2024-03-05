//interleaving string:https://leetcode.com/problems/interleaving-string/?envType=study-plan-v2&envId=top-interview-150

/*
My recursive idea. Fails at:
s1 =
"aabcc"
s2 =
"dbbca"
s3 =
"aadbbbaccc"

*/

class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if(s1.indexOf(s3) != -1 || s2.indexOf(s3) != -1)return true;

        return getInterleave(s1,s2,s3);
    }
    private boolean getInterleave(String s1, String s2, String s3){
        int s2Ind = s2.indexOf(s3.substring(s3.length()-1));
        int s1Ind = s1.indexOf(s3.substring(s3.length()-1));
        if(( s2Ind != -1) && 
            (isInterleave(s1,s2.substring(0,s2Ind),s3.substring(0,s3.length()-1)) || 
            (isInterleave(s1,s2.substring(s2Ind+1),s3.substring(0,s3.length()-1))))){
                return true;
        }
        if((s1Ind != -1) &&
            (isInterleave(s1.substring(0,s1Ind),s2,s3.substring(0,s3.length()-1)) || 
            (isInterleave(s1.substring(s1Ind+1),s2,s3.substring(0,s3.length()-1))))
        ){
            return true;
        }

        s2Ind = s2.indexOf(s3.substring(0,1));
        s1Ind = s1.indexOf(s3.substring(0,1));
        if(( s2Ind != -1) && 
            (isInterleave(s1,s2.substring(0,s2Ind),s3.substring(1)) || 
             isInterleave(s1,s2.substring(s2Ind+1),s3.substring(1)))
        ){
                return true;
        }
        if((s1Ind != -1) &&
            (isInterleave(s1.substring(0,s1Ind),s2,s3.substring(1)) || 
             isInterleave(s1.substring(s1Ind+1),s2,s3.substring(1)))
        ){
            return true;
        }
        return false;
    }
}

/*
Found a more intuitive recursive solution
*/

class Solution {
    char[] s1c, s2c,s3c;
    int l1,l2,l3;
    
    public boolean isInterleave(String s1, String s2, String s3) {
        l1 = s1.length();
        l2 = s2.length();
        l3 = s3.length();


        s1c = s1.toCharArray();
        s2c = s2.toCharArray();
        s3c = s3.toCharArray();

        if(l1 + l2 != l3)return false;

        return il(0,0,0);
    }
    private boolean il(int p1, int p2, int p3){
        if(p3 == l3)return true;
        boolean res = false;
        

        if(p1 < l1 && s1c[p1] == s3c[p3]){
            res = res || il(p1+1,p2,p3+1);
        }

        if(res){
            return res;
        }

        if(p2 < l2 && s2c[p2] == s3c[p3]){
            res = res || il(p1,p2+1,p3+1);
        }
        return res;
    }
}

/*
DP of that recursion, still TLE
*/

class Pair{
    int a, b;
    public Pair(int a,int b){
        this.a = a;
        this.b = b;
    }
    @Override
    public boolean equals(Object o){
        if(o == this)return true;
        if(!(o instanceof Pair))return false;

        Pair p = (Pair)o;

        return p.a == this.a && p.b == this.b;
    }
}
class Solution {
    char[] s1c, s2c,s3c;
    Map<Pair, Boolean> dp;
    int l1,l2,l3;
    
    public boolean isInterleave(String s1, String s2, String s3) {
        l1 = s1.length();
        l2 = s2.length();
        l3 = s3.length();

        dp = new HashMap<>();

        s1c = s1.toCharArray();
        s2c = s2.toCharArray();
        s3c = s3.toCharArray();

        if(l1 + l2 != l3)return false;

        return il(0,0,0);
    }
    private boolean il(int p1, int p2, int p3){
        if(p3 == l3)return true;
        boolean res = false;
        Pair p = new Pair(p1, p2);
        if(dp.get(p) != null)return dp.get(p);

        if(p1 < l1 && s1c[p1] == s3c[p3]){
            res = res || il(p1+1,p2,p3+1);
        }

        if(res){
            dp.put(p,res);
            return res;
        }

        if(p2 < l2 && s2c[p2] == s3c[p3]){
            res = res || il(p1,p2+1,p3+1);
        }
        dp.put(p,res);
        return res;
    }
}

/*
Used a better DP
*/
class Solution {
    char[] s1c, s2c,s3c;
    int l1,l2,l3;
    int[][] dp;
    public boolean isInterleave(String s1, String s2, String s3) {
        l1 = s1.length();
        l2 = s2.length();
        l3 = s3.length();

        dp = new int[l1+1][l2+1];
        
        for(int[] arr1 : dp) 
            Arrays.fill(arr1, -1);

        s1c = s1.toCharArray();
        s2c = s2.toCharArray();
        s3c = s3.toCharArray();

        if(l1 + l2 != l3)return false;

        return il(0,0,0);
    }
    private boolean il(int p1, int p2, int p3){
        if(p3 == l3)return true;
        boolean res = false;

        if(dp[p1][p2] != -1){
            return dp[p1][p2] == 0 ? false : true;
        }

        if(p1 < l1 && s1c[p1] == s3c[p3]){
            res = res || il(p1+1,p2,p3+1);
        }

        if(res){
            dp[p1][p2] = res ? 1 : 0;
            return res;
        }

        if(p2 < l2 && s2c[p2] == s3c[p3]){
            res = res || il(p1,p2+1,p3+1);
        }
        dp[p1][p2] = res ? 1 : 0;
        return res;
    }
}
