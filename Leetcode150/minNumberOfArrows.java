//Minimum number of arrows: https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/description/?envType=study-plan-v2&envId=top-interview-150

/*

This is the greedy approach which did not work in this test case:
[[3,9],[7,12],[3,8],[6,8],[9,10],[2,9],[0,9],[3,9],[0,6],[2,8]]

*/

/*
class SortByFirst implements Comparator<int[]>{
    public int compare(int[] v1, int[] v2){
        return Integer.compare(v1[0],v2[0]);
    }
}
class Solution {
    private boolean overlap(int[] v1, int[] v2){
        if(v1[1] >= v2[0] && v1[0] <= v2[0]){
            return true;
        }else if(v2[1] >= v1[0] && v2[0] <= v1[0]){
            return true;
        }
        return false;
    }
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, new SortByFirst());
        int[] used = new int[points.length];

        int count = 0;
        for(int i=1;i<points.length;i++){
            if(used[i] == 1)continue;
            if(used[i-1]==0 && used[i]==0 && overlap(points[i-1],points[i])){
                used[i-1] = 1;
                used[i] = 1;
            }
        }
        for(int i=0;i<used.length;i++){
            if(used[i] == 0)count++;
            if(used[i]==1){
                count++;
                i++;
            }
        }
        return count;
    }
}*/


/*

So, it was greedy only but all you had to do is after sorting all the intervals in ascending order, you just had to find out what was the last possible x to the right.
Once we found that, all the intervals could be handled with the same arroww !

Pretty interesting and the solution is also very intuitive
*/

class Solution {
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, (a,b) -> Integer.compare(a[0],b[0]));

        int xEnd = points[0][1];

        int count = 1;

        for(int i=0;i<points.length;i++){
            if(points[i][0] <= xEnd){
                xEnd = Math.min(xEnd, points[i][1]);
            }else{
                count++;
                xEnd = points[i][1];
            }
        }

        return count;
    }
}