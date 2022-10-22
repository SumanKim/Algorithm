package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_11909 {

	static int n;
	static int[][] arr;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int dp[][] = new int[n][n];
		for(int i=0; i<n; i++)
			Arrays.fill(dp[i], Integer.MAX_VALUE);
		dp[0][0] = 0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(i >= 1) {
					if(arr[i-1][j] > arr[i][j])
						dp[i][j] = Math.min(dp[i][j], dp[i-1][j]);
					else
						dp[i][j] = Math.min(dp[i][j], dp[i-1][j]+arr[i][j]-arr[i-1][j]+1);
				}
				if(j >= 1) {
					if(arr[i][j-1] > arr[i][j])
						dp[i][j] = Math.min(dp[i][j], dp[i][j-1]);
					else
						dp[i][j] = Math.min(dp[i][j], dp[i][j-1]+arr[i][j]-arr[i][j-1]+1);
				}
				
			}
		}
		System.out.println(dp[n-1][n-1]);
	}

}
