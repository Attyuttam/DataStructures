//Insert intervals: https://leetcode.com/problems/insert-interval/description/?envType=study-plan-v2&envId=top-interview-150


/*
This solution first finds the max overlap interval
Then does a second iteration and merges each interval with the max found interval and if there is no overlap then it simply puts the interval
This logic does everything in place with 2 iterations, so its essentially O(n)
*/
class Solution {
    public int[] merge(int[] v1, int[] v2){
        int[] n = new int[2];
        n[0] = v1[0];
        n[1] = Math.max(v1[1],v2[1]);

        return n;
    }
    public boolean overlap(int[] v1, int[] v2){
        if(v1[1] >= v2[0] && v1[0] <= v2[0]){
            return true;
        }else if(v2[1] >= v1[0] && v2[0] <= v1[0]){
            return true;
        }
        return false;
    }
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int[] track = newInterval;

        for(int i=0;i<intervals.length;i++){
            if(track[0] <= intervals[i][1] && track[0] >= intervals[i][0]){
                track = merge(intervals[i], track);
            }else if(intervals[i][0] <= track[1] && intervals[i][0] >= track[0]){
                track = merge(track, intervals[i]);
            }
        }

        List<int[]> res = new ArrayList<>();
        res.add(track);

        for(int i=0;i<intervals.length;i++){
            int[] last = res.get(res.size()-1);
            if(!overlap(intervals[i],last)){
            
                if(intervals[i][1] < last[0]){
                    res.set(res.size()-1, intervals[i]);
                    res.add(last);
                }else{
                    res.add(intervals[i]);
                }
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}