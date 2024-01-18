import java.io.*;
import java.util.Scanner;

public class splitFuntionality{
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		
		String sentence = sc.nextLine();
		
		String[] splitBySlash = sentence.split("/");
		
		for(int i=0;i<splitBySlash.length;i++){
			System.out.println(splitBySlash[i]);
		}
	}
}