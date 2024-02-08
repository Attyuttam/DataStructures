//Minimum genetic mutation: https://leetcode.com/problems/minimum-genetic-mutation/description/?envType=study-plan-v2&envId=top-interview-150

/*
Beats 79.88%

Also, even though my solution is accepted by the Leetcode judge, there is a test case I found that fails for 
my given solution.

Start gene: AACCGGTT
End gene: GACCGGTT

Bank: ["CACCGGTT","GACCGGTT"]

For this my code returns output 2 but it should be 1

Although, didn't try this but it claims to be 100% faster, check this:
https://leetcode.com/problems/minimum-genetic-mutation/solutions/3090587/java-solution-0-ms-beats-100

In this solution (link above), the user rather than changing the current string, it checks for
each element in the bank which requires only mutation for current gene to convert to the one in the bank
If it finds one, it puts in the queue
*/
class Solution {
    HashMap<String,Boolean> setBank = new HashMap<>();
    int mm = Integer.MAX_VALUE;
    private void minMutationHelper(String cg, String eg, int cm){
        if(cg.equals(eg)){
            mm = Math.min(cm,mm);
            return;
        }

        for(int i=0;i<8;i++){
            StringBuilder cgTemp = new StringBuilder(cg);
            
                cgTemp.setCharAt(i,'A');
                
                if(!cg.equals(cgTemp.toString()) && setBank.get(cgTemp.toString())!=null && setBank.get(cgTemp.toString()) == false){
                    setBank.put(cgTemp.toString(), true);
                    minMutationHelper(cgTemp.toString(),eg,cm+1);
                }
                
                cgTemp.delete(0, cgTemp.length());
                cgTemp.append(cg);
           
                cgTemp.setCharAt(i,'C');
                
                if(!cg.equals(cgTemp.toString()) && setBank.get(cgTemp.toString())!=null && setBank.get(cgTemp.toString()) == false){
                    setBank.put(cgTemp.toString(), true);
                    minMutationHelper(cgTemp.toString(),eg,cm+1);
                }
               
                cgTemp.delete(0, cgTemp.length());
                cgTemp.append(cg);

                cgTemp.setCharAt(i,'G');

                if(!cg.equals(cgTemp.toString()) && setBank.get(cgTemp.toString())!=null && setBank.get(cgTemp.toString()) == false){
                    setBank.put(cgTemp.toString(), true);
                    minMutationHelper(cgTemp.toString(),eg,cm+1);
                }
                cgTemp.delete(0, cgTemp.length());
                cgTemp.append(cg);
           
                cgTemp.setCharAt(i,'T');
                
                if(!cg.equals(cgTemp.toString()) && setBank.get(cgTemp.toString())!=null && setBank.get(cgTemp.toString()) == false){
                    setBank.put(cgTemp.toString(), true);
                    minMutationHelper(cgTemp.toString(),eg,cm+1);
                }
            
        }
    }
    public int minMutation(String startGene, String endGene, String[] bank) {
        for(int i=0;i<bank.length;i++){
            setBank.put(bank[i], false);
        }
        if(setBank.get(endGene) == null)return -1;
        minMutationHelper(startGene, endGene, 0);
        return mm == Integer.MAX_VALUE ? -1 : mm;
    }
}

/*

The one that beats 100%

*/


class Solution {
    private int getNumMut(String curr, String next){
        int count = 0;
        for(int i=0;i<curr.length();i++){
            if(curr.charAt(i) != next.charAt(i))count++;
        }
        return count;
    }
    public int minMutation(String sg, String eg, String[] bank) {
        if(sg == eg)return 0;

        Map<String, Boolean> bs = new HashMap<>();
        for(int i=0;i<bank.length;i++)bs.put(bank[i],false);

        if(bs.get(eg) == null)return -1;

        Queue<String> q = new LinkedList<>();
        q.add(sg);

        if(bs.get(sg) == null){
            bs.put(sg,true);
        }

        List<Character> pm = new ArrayList<>();
        pm.add('A');
        pm.add('C');
        pm.add('G');
        pm.add('T');

        int steps = 0;
        while(!q.isEmpty()){
            //this size thing is to ensure we are handling one level at a time
            int size = q.size();    
            for(int i=0;i<size;i++){
                String curr = q.poll();
                
                for(String next : bs.keySet()){
                    if(bs.get(next) == false){
                        int numMut = getNumMut(curr, next);
                        if(numMut == 1){
                            if(next.equals(eg))return steps+1;

                            q.add(next);
                            bs.put(next, true);
                        }
                    }
                }
            }
            steps++;
        }
        return -1;
    }
}