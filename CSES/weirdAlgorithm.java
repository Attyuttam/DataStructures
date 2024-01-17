import java.io.*;
import java.util.Scanner;  


public class weirdAlgorithm{

	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		long v = sc.nextInt();
		
		System.out.print(v+" ");
		while(v!=1){
			if(v%2==0){
				v/=2;
			}else{
				v = v*3 + 1;
			}
			System.out.print(v+" ");
		}
	}
}