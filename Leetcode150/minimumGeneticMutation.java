//Minimum genetic mutation: https://leetcode.com/problems/minimum-genetic-mutation/description/?envType=study-plan-v2&envId=top-interview-150

/*
Beats 79.88%

Although, didn't try this but it claims to be 100% faster, check this:
https://leetcode.com/problems/minimum-genetic-mutation/solutions/3090587/java-solution-0-ms-beats-100
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