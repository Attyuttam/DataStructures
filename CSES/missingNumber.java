import java.util.Arrays;
import java.util.Scanner;
import java.io.*;

//This is a O(log n)) solution, yet its TLE
/*
public class missingNumber {
	
	private static int findMissing(int[] arr, int s,int e){
		int m = -1;
		while(s<=e){
			m = s + (e-s)/2;
			
			if(m-1>=0 && m+1<arr.length && arr[m-1] == m && arr[m+1] == m+3){
				if(arr[m]-2==arr[m-1]){
					return arr[m-1]+1;
				}else{
					return arr[m]+1;
				}
			}
			
			if(m+2 == arr[m])e = m-1;
			else s = m+1;
		}
		return arr[0]!=1?arr[0]-1:arr[arr.length-1]+1;
	}
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		n-=1;
		int arr[] = new int[n];
		
		for(int i=0;i<n;i++){
			arr[i] = sc.nextInt();
		}
		
		Arrays.sort(arr);
		
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i]+" ");
		}
		if(arr[arr.length-1] == arr.length){
			System.out.println(arr[0]!=1?arr[0]-1:arr[arr.length-1]+1);
		}else{
			System.out.println(findMissing(arr,0,arr.length-1));
		}
	}
}
*/
/*
This logic uses the power of XOR. Sincce XOR of any two same numbers is 0, we do a XOR of 1 through n, then we keep on XORing for all the inputs
So, eventually we will be left with the number that was missing in the list of numbers provided as input
*/
public class missingNumber{
	public static void main(String args[]){
			Scanner sc = new Scanner(System.in);
			int n = sc.nextInt();
			
			int lks = 0;
			int v = 0;
			
			for(int i=1;i<=n;i++){
				lks^=i;
			}
			for(int i=0;i<n-1;i++){
				v = sc.nextInt();
				lks^=v;
			}
			System.out.println(lks);
	}
}