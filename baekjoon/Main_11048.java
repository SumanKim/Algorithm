package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11048 {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] maze = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) 
				maze[i][j] = Integer.parseInt(st.nextToken());
		}
		
		int dp[][] = new int[N][M];
		dp[0][0] = maze[0][0];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(i >= 1 && j >= 1) 
					dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1]+maze[i][j]); 
				if(i >= 1) 
					dp[i][j] = Math.max(dp[i][j], dp[i-1][j]+maze[i][j]);
				if(j >= 1) 
					dp[i][j] = Math.max(dp[i][j], dp[i][j-1]+maze[i][j]);
			}
		}
		System.out.println(dp[N-1][M-1]);
	}
}
