package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//메이플스토리
public class Main_20925 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		int c[] = new int[N];
		int e[] = new int[N];
		int t[][] = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			c[i] = Integer.parseInt(st.nextToken());
			e[i] = Integer.parseInt(st.nextToken());
		}
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				t[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int dp[][] = new int[T+1][N];
		for(int i=1; i<=T; i++) {
			for(int j=0; j<N; j++) {
				if(dp[i-1][j] >= c[j])
					dp[i][j] = Math.max(dp[i][j], dp[i-1][j]+e[j]);
				for(int k=0; k<N; k++) {
					if(i >= t[k][j] && dp[i-t[k][j]][k] >= c[j])
						dp[i][j] = Math.max(dp[i][j], dp[i-t[k][j]][k]);
				}
			}
		}
		
		int ans = 0;
		for(int i=0; i<N; i++) {
			ans = Math.max(ans, dp[T][i]);
		}
		
		System.out.println(ans);
	}

}
