//Search in a 2D matrix: https://leetcode.com/problems/search-a-2d-matrix/description/?envType=study-plan-v2&envId=top-interview-150


/*
My solution, beats 100%

*/

class Solution {
    private boolean findVal(int[] mat,int s, int e,int target){
        
        while(s<=e){
            int m = s + (e-s)/2;
            if(mat[m] == target){
                return true;
            }
            if(target<mat[m]){
                e=m-1;
            }else{
                s=m+1;
            }
        }
        return false;
    }
    private boolean findRow(int[][] matrix,int s,int e,int target){
        while(s<=e){
            int m = s + (e-s)/2;
            if(matrix[m][0]<=target && target<=matrix[m][matrix[m].length-1]){
                return findVal(matrix[m],0,matrix[m].length-1,target);
            }
            if(target<matrix[m][0]){
                e=m-1;
            }else{
                s=m+1;
            }
        }
        return false;
    }
    public boolean searchMatrix(int[][] matrix, int target) {
        /*
            1. find row using binary search
            2. find value in row using binary search
        */
        return findRow(matrix,0,matrix.length-1,target);
    }
}