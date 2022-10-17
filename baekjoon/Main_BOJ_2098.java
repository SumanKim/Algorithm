package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//TSP - 외판원 순회 : 비트마스킹 + dp + 재귀
public class Main_BOJ_2098 {

	static final int INF = Integer.MAX_VALUE/2;
	static int N, res;
	static int[][] W;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		W = new int[N][N];
		dp = new int[N][1<<N];
		for(int i=0; i<N; i++)
			Arrays.fill(dp[i], INF);
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) 
				W[i][j] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(tsp(0,1));
	}

	static int tsp(int cur, int check) {
		if(check == (1<<N) - 1) {
			if(W[cur][0] == 0) return INF;
			return W[cur][0];
		}
		
		if(dp[cur][check] != INF) return dp[cur][check];
		
		for(int i=0; i<N; i++) {
			if((check & (1<<i)) != 0 || W[cur][i] == 0) continue;
			dp[cur][check] = Math.min(dp[cur][check], tsp(i, check | (1<<i)) + W[cur][i]);
		}
		
		return dp[cur][check];
	}
}
