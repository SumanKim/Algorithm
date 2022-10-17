package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//1,2,3 더하기
public class Main_9095_dp {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t=0; t<T; t++) {
			int n = Integer.parseInt(br.readLine());
			int dp[] = new int[12];
			
			dp[1] = 1;
			dp[2] = 2;
			dp[3] = 4;
			for(int i=4; i<=11; i++) {
				dp[i] = dp[i-3] + dp[i-2] + dp[i-1];
			}
			
			sb.append(dp[n]+"\n");
		}
		System.out.println(sb.toString());
	}

}
