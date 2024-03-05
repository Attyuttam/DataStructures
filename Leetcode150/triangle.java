//Triangle:https://leetcode.com/problems/triangle/description/?envType=study-plan-v2&envId=top-interview-150

/*
Simple intuitive solution, but this beats only 5.36%
*/

class Solution {
    public int minimumTotal(List<List<Integer>> t) {
        List<List<Integer>> res = new ArrayList<>();
        for(int i=0;i<t.size();i++){
            List<Integer> temp = new ArrayList<>();
            for(int j=0;j<t.get(i).size();j++){
                temp.add(Integer.MAX_VALUE);
            }
            res.add(temp);
        }
        List<Integer> tempVal = new ArrayList<>();
        tempVal.add(t.get(0).get(0));
        res.set(0,tempVal);

        for(int i=0;i<t.size()-1;i++){
            for(int j=0;j<t.get(i).size();j++){
                int val1 = res.get(i).get(j) + t.get(i+1).get(j);
                int val2 = res.get(i).get(j) + t.get(i+1).get(j+1);
                if(val1 < res.get(i+1).get(j)){
                    List<Integer> temp = res.get(i+1);
                    temp.set(j,val1);
                }
                if(val2 < res.get(i+1).get(j+1)){
                    List<Integer> temp = res.get(i+1);
                    temp.set(j+1,val2);
                }
            }
        }
        
        int minVal = Integer.MAX_VALUE;
        for(int i=0;i<t.get(t.size()-1).size();i++){
            minVal = Math.min(minVal, res.get(res.size()-1).get(i));
        }
        return minVal;
    }
}

/*
Improved a little bit, but still beats only 5.36%
*/

class Solution {
    public int minimumTotal(List<List<Integer>> t) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(Arrays.asList(t.get(0).get(0)));

        for(int i=0;i<t.size()-1;i++){
            for(int j=0;j<t.get(i).size();j++){
                int val1 = res.get(i).get(j) + t.get(i+1).get(j);
                int val2 = res.get(i).get(j) + t.get(i+1).get(j+1);
                if(j==0){
                    res.add(new ArrayList<Integer>(Arrays.asList(val1,val2)));
                    continue;
                }
                if(val1 < res.get(i+1).get(j)){
                    List<Integer> temp = res.get(i+1);
                    temp.set(j,val1);
                }
                List<Integer> temp = res.get(i+1);
                temp.add(val2);
            }
        }
        
        int minVal = Integer.MAX_VALUE;
        for(int i=0;i<t.get(t.size()-1).size();i++){
            minVal = Math.min(minVal, res.get(res.size()-1).get(i));
        }
        return minVal;
    }
}

/*
Improved a bit more, but beats only 9.17%.
Removed the dependency on the 2D array
*/
class Solution {
    public int minimumTotal(List<List<Integer>> t) {
        List<Integer> res = new ArrayList<>();
        res.add(t.get(0).get(0));

        for(int i=0;i<t.size()-1;i++){
            List<Integer> tempRes = new ArrayList<>();
            for(int j=0;j<t.get(i).size();j++){
                int val1 = res.get(j) + t.get(i+1).get(j);
                int val2 = res.get(j) + t.get(i+1).get(j+1);

                if(j==0){
                    tempRes = new ArrayList<Integer>(Arrays.asList(val1,val2));
                    continue;
                }
                if(val1 < tempRes.get(j)){
                    tempRes.set(j,val1);
                }
                tempRes.add(val2);
            }
            res = tempRes;
        }
        
        int minVal = Integer.MAX_VALUE;
        for(int i=0;i<t.get(t.size()-1).size();i++){
            minVal = Math.min(minVal, res.get(i));
        }
        return minVal;
    }
}

/*
Replaced array lists with arrays and significantly improved performance to beating 31.9% users
*/

class Solution {
    public int minimumTotal(List<List<Integer>> t) {
        int[] res = new int[1];
        res[0] = t.get(0).get(0);

        for(int i=0;i<t.size()-1;i++){
            int[] tempRes = new int[0];
            for(int j=0;j<t.get(i).size();j++){
                int val1 = res[j] + t.get(i+1).get(j);
                int val2 = res[j] + t.get(i+1).get(j+1);

                if(j==0){
                    tempRes = new int[t.get(i+1).size()];
                    tempRes[0] = val1;
                    tempRes[1] = val2;
                    continue;
                }
                if(val1 < tempRes[j]){
                    tempRes[j] = val1;
                }
                tempRes[j+1] = val2;
            }
            res = tempRes;
        }
        
        int minVal = Integer.MAX_VALUE;
        for(int i=0;i<res.length;i++){
            minVal = Math.min(minVal, res[i]);
        }
        return minVal;
    }
}

/*
Removed the last loop to get the min val, biut still beats 31.9% only
*/
class Solution {
    public int minimumTotal(List<List<Integer>> t) {
        int[] res = new int[1];
        res[0] = t.get(0).get(0);

        int minVal = Integer.MAX_VALUE;
        for(int i=0;i<t.size()-1;i++){
            int[] tempRes = new int[0];
            minVal = Integer.MAX_VALUE;
            for(int j=0;j<t.get(i).size();j++){
                int val1 = res[j] + t.get(i+1).get(j);
                int val2 = res[j] + t.get(i+1).get(j+1);

                if(j==0){
                    tempRes = new int[t.get(i+1).size()];
                    tempRes[0] = val1;
                    tempRes[1] = val2;
                    minVal = Math.min(val1,val2);
                    continue;
                }
                if(val1 < tempRes[j]){
                    tempRes[j] = val1;
                }
                tempRes[j+1] = val2;
                minVal = Math.min(minVal,Math.min(val1,val2));
            }
            res = tempRes;
        }
    
        return minVal == Integer.MAX_VALUE ? t.get(0).get(0) : minVal;
    }
}

/*
Found it in the fastest solution list.
This is pretty interesting!

The idealogy shift is that rather than going from top to bottom what if we go from bottom to top !!
*/

class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int[] dp = new int[triangle.size() + 1];
        minPathSum(triangle, 0, dp);
        return dp[0];
    }

    private static void minPathSum(List<List<Integer>> triangle, int k, int[] dp){
        if(k < triangle.size() - 1) minPathSum(triangle, k+1, dp);
        for(int i = 0; i < triangle.get(k).size(); i++){
            dp[i] = triangle.get(k).get(i) + Math.min(dp[i], dp[i+1]);
        }
    }
}