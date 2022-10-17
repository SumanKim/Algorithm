package baekjoon;

import java.util.Scanner;

//Four Squares
public class Main_17626 {

	static int res = 5;
	static int[] dp;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		dp = new int[n+1];
		for(int i=0; i<=n; i++)
			dp[i] = Integer.MAX_VALUE;
		
		for(int i=1; i<=n; i++) {
			for(int j=(int)Math.sqrt(i); j>0; j--) {
				if(i == j*j) {
					dp[i] = 1;
					break;
				}
				else
					dp[i] = Math.min(dp[i], dp[i-j*j] + 1);
			}
		}
		System.out.println(dp[n]);
	}
	
}
