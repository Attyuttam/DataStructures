//repetitions: https://cses.fi/problemset/task/1069

import java.io.*;
import java.util.Scanner;
import java.util.Arrays;

public class repetitions{
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		
		if(s.length() == 0){
			System.out.println(0);
			return;
		}
		if(s.length() == 1){
			System.out.println(1);
			return;
		}
		
		int arr[] = new int[s.length()];
		
		char le = s.charAt(0);
		arr[0] = 1;
		int maxValue = -1;
		for(int i=1;i<s.length();i++){
			if(s.charAt(i) == le){
				arr[i] = arr[i-1]+1;
			}else{
				arr[i] = 1;
				le = s.charAt(i);
			}
			maxValue = Math.max(maxValue, arr[i]);
		}
		System.out.println(maxValue);
	}
}

