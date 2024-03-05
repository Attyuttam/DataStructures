//max points on a line: https://leetcode.com/problems/max-points-on-a-line/description/?envType=study-plan-v2&envId=top-interview-150

/*
Brute force. Beats 10.31%
*/

class Solution {
    public int maxPoints(int[][] points) {
        if(points.length == 1)return 1;
        
        int n = points.length;
        int max = Integer.MIN_VALUE;
        for(int i=0;i<n-1;i++){
            for(int j=i+1;j<n;j++){
                max = Math.max(max, count(i,j,points));
            }
        }
        return max;
    }
    private int count(int p1Ind, int p2Ind, int[][] points){
        int[] p1 = points[p1Ind];
        int[] p2 = points[p2Ind];
        int count = 0;
        for(int i=0;i<points.length;i++){
            int[] p3 = points[i];

            if(i == p1Ind || i == p2Ind || slopeCheck(p1,p2,p3)){
                count++;
            }
        }
        return count;
    }
    private boolean slopeCheck(int[] p1, int [] p2, int[] p3){
        return ((p2[1] - p1[1])*(p3[0]-p1[0])) == ((p3[1] - p1[1])*(p2[0]-p1[0]));
    }
}

/*
Small tweak in understanding and beats 77.8%
*/

class Solution {
    public int maxPoints(int[][] points) {
        if(points.length == 1)return 1;
        
        int n = points.length;
        int max = Integer.MIN_VALUE;
        
        for(int i=0;i<n;i++){
            Map<Double,Integer> slopeToCount = new HashMap<>();
            for(int j=i+1;j<n;j++){
                double slope = getSlope(points[i],points[j]);
                slopeToCount.put(slope, slopeToCount.getOrDefault(slope,0)+1);
                max = Math.max(max, slopeToCount.get(slope)+1);
            }
        }
        return max;
    }
    private double getSlope(int[] p1, int [] p2){
        if(p2[0]-p1[0] == 0)return Double.POSITIVE_INFINITY;
        double val = ((p2[1] - p1[1])/(double)(p2[0]-p1[0]));
        //This is because sometimes you get a -0.0 which produces wrong  result
        if(val == 0)return 0.0;
        return val;
    }
}