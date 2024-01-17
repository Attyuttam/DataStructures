//Merge Intervals: https://leetcode.com/problems/merge-intervals/submissions/1148482977/?envType=study-plan-v2&envId=top-interview-150

/*
	Although prety trivial, this solution we have to keep in mind that the last merged interval might get updated.
	So, everytime we merge an interval, we should be comparing the next interval with the last merged interval
	Edge  case: [[2,3],[4,5],[6,7],[8,9],[1,10]]
*/

class IntervalSort implements Comparator<int[]>{
    public int compare(int[] a, int[] b){
        return a[0] - b[0];
    }
}
class Solution {
    public int[][] merge(int[][] intervals) {
        List<int[]> res = new ArrayList<>();

        //sort the list of intervals
        Arrays.sort(intervals, new IntervalSort());

        //start working on the list of sorted intervals
        res.add(intervals[0]);
        for(int i=1;i<intervals.length;i++){
            int[] lastMerged = res.get(res.size()-1);
            if(lastMerged[1] < intervals[i][0]){
                res.add(intervals[i]);
            }else{
                int[] sub_res = new int[2];
                sub_res[0] = lastMerged[0];
                sub_res[1] = Math.max(intervals[i][1],lastMerged[1]);

                res.set(res.size()-1,sub_res);
            }
        }
        return res.toArray(new int[res.size()-1][]);
    }
}