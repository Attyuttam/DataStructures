//Word ladder: https://leetcode.com/problems/word-ladder/description/?envType=study-plan-v2&envId=top-interview-150
/*
Beats only 5%
*/
class Solution {
    private int getNumChange(String curr, String next){
        int count = 0;
        for(int i=0;i<curr.length();i++){
            if(curr.charAt(i) != next.charAt(i))count++;
        }
        return count;
    }
    public int ladderLength(String bw, String ew, List<String> bank) {
        if(bw.equals(ew) || !bank.contains(ew))return 0;
        
        HashMap<String,Boolean> bl = new HashMap<>();

        for(String w : bank){
            bl.put(w,false);
        }

        if(bl.get(bw) == null){
            bl.put(bw, true);
        }

        Queue<String> q = new LinkedList<>();
        q.add(bw);

        int steps = 1;
        while(!q.isEmpty()){
            int size = q.size();

            for(int i=0;i<size;i++){
                String curr = q.poll();
                for(String next : bl.keySet()){
                    if(curr.length() != next.length() || bl.get(next) == true)continue;
                    int numChange = getNumChange(curr, next);

                    if(numChange == 1){
                        if(next.equals(ew))return steps+1;
                        q.add(next);
                        bl.put(next, true);
                    }
                }
            }
            steps++;
        }
        return 0;
    }
}